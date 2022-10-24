package com.qfedu.fmmall.service;

import com.qfedu.fmmall.entity.ShoppingCart;
import com.qfedu.fmmall.entity.ShoppingCartVO;
import com.qfedu.fmmall.vo.ResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCartService {
    public ResultVO addShoppingCart(ShoppingCart cart);

    public ResultVO listShoppingCartsByUserId(int userId);

    public ResultVO updateCartNum(int cartId,int cartNum);


}
