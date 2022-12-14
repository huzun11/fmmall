package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.OrderItemMapper;
import com.qfedu.fmmall.dao.OrdersMapper;
import com.qfedu.fmmall.dao.ProductSkuMapper;
import com.qfedu.fmmall.dao.ShoppingCartMapper;
import com.qfedu.fmmall.entity.OrderItem;
import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.entity.ProductSku;
import com.qfedu.fmmall.entity.ShoppingCartVO;
import com.qfedu.fmmall.service.OrderService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ProductSkuMapper productSkuMapper;

    @Override
    @Transactional//spring整合mybatis事务管理，将整个方法里的四个操作数据库步骤当成一个
    public ResultVO addOrder(String cids,Orders order) throws SQLException {

        //一、校验库存 根据cids查询当前订单中关联的购物车记录详情（包括库存）
        String[] arr = cids.split(",");
        List<Integer> cidsList = new ArrayList<>();
        for (int i = 0; i <arr.length ; i++) {
            cidsList.add(Integer.parseInt(arr[i]));
        }
        List<ShoppingCartVO> list = shoppingCartMapper.selectShopcartByCids(cidsList);

        boolean f = true;
        String untitled = "";
        for (ShoppingCartVO sc: list) {
            if(Integer.parseInt(sc.getCartNum()) > sc.getSkuStock()){
                f = false;
            }
            //获取所有商品名称，以,分割拼接成字符串
            untitled = untitled+sc.getProductName()+",";
        }
        if(f){
            //二、保存订单
            //3.表示库存充足--保存订单
            //a.userId
            //b.untitled
            //c.收货人信息:接受传过来的姓名，电话，地址
            //d.总价格
            //e.支付方式
            //f.订单创建时间、
            //g.订单初始状态(待支付 1 √)
            order.setUntitled(untitled);
            order.setCreateTime(new Date());
            order.setStatus("1");

            //生成订单编号
            String orderId = UUID.randomUUID().toString().replace("-", ",");
            order.setOrderId(orderId);


            //保存订单
            int i = ordersMapper.insert(order);

            if(i>0){
                //保存订单成功
                //三、生成商品快照
              //  List<OrderItem>  orderItems = new ArrayList<>();
                for (ShoppingCartVO sc: list) {
                    int cnum = Integer.parseInt(sc.getCartNum());
                    String itemId = System.currentTimeMillis()+""+ (new Random().nextInt(89999)+10000);
                    OrderItem orderItem = new OrderItem(itemId, orderId, sc.getProductId(), sc.getProductName(), sc.getProductImg(), sc.getSkuId(),
                            sc.getSkuName(), new BigDecimal(sc.getSellPrice()),
                            cnum, new BigDecimal(sc.getSellPrice() * cnum),
                            new Date(), new Date(), 0);
                    //保存订单
                    orderItemMapper.insert(orderItem);
                    //  orderItems.add(orderItem);


                }
            //    int j = orderItemMapper.insertList(orderItems);

                //四、扣减库存,根据套餐ID修改套餐库存量
                // 商品1    奥利啊小饼干 套餐ID  4    2    500
                //商品2     咪咪虾条    套餐ID  1    2    127
                for (ShoppingCartVO sc: list) {
                    String skuId = sc.getSkuId();
                    int newStock = sc.getSkuStock() - Integer.parseInt(sc.getCartNum());

//                    Example example = new Example(ProductSku.class);
//                    Example.Criteria criteria = example.createCriteria();
//                    criteria.andEqualTo("skuId",skuId);

                   // ProductSku productSku = productSkuMapper.selectByPrimaryKey(skuId);

                     //ProductSku productSku = new ProductSku();
                    //productSku.setStock(newStock);
                    //productSku.setSkuImg(null);
                   // int k = productSkuMapper.updateByExample(productSku, example);
                    ProductSku productSku = new ProductSku();
                    productSku.setSkuId(skuId);
                    productSku.setStock(newStock);
                    productSkuMapper.updateByPrimaryKeySelective(productSku);
                }
                //五、删除购物车，当购物车里的商品购买完成之后，应该删除掉
                for (int cid :cidsList) {
                    shoppingCartMapper.deleteByPrimaryKey(cid);
                    
                }

            }
                return new ResultVO(ResStatus.OK,"下单成功",orderId);

        }else{
            //表示库存不足
            return new ResultVO(ResStatus.NO,"库存不足，下单失败",null);
        }

    }
}
