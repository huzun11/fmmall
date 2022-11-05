package com.qfedu.fmmall.controller;

import com.qfedu.fmmall.dao.OrdersMapper;
import com.qfedu.fmmall.entity.Orders;
import com.qfedu.fmmall.service.OrderService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@CrossOrigin
@RequestMapping("/order")
@Api(value = "提供订单相关的接口",tags = "订单管理")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResultVO add(String cids,@RequestBody Orders order){
        ResultVO resultVO = null;
        try {
            resultVO = orderService.addOrder(cids, order);
        } catch (SQLException e) {
            e.printStackTrace();
            resultVO = new ResultVO(ResStatus.NO,"提交订单失败！",null);
        }
        return resultVO;
    }
}
