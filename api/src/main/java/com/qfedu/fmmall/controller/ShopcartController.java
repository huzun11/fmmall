package com.qfedu.fmmall.controller;



import com.qfedu.fmmall.entity.ShoppingCart;
import com.qfedu.fmmall.service.ShoppingCartService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopcart")
@Api(value = "提供购物车业务相关接口",tags = "购物车管理")
@CrossOrigin
public class ShopcartController {


    @Autowired
    private ShoppingCartService shoppingCartService;

    @ApiOperation("添加购物车接口")
    @PostMapping("/add")
    public ResultVO addShoppingCart(@RequestBody ShoppingCart cart,@RequestHeader("token") String token){
        ResultVO resultVO = shoppingCartService.addShoppingCart(cart);
        return resultVO;
    }

    @ApiOperation("购物车列表接口")
    @GetMapping("/list")
    @ApiImplicitParam(dataType = "int",name = "userId", value = "用户ID",required = true)
    public ResultVO list(Integer userId,@RequestHeader("token")String token){
          ResultVO resultVO = shoppingCartService.listShoppingCartsByUserId(userId);
          return resultVO;

    }

    @PutMapping("/update/{cid}/{cnum}")
    public ResultVO updateNum(@PathVariable("cid") Integer cateId,
                              @PathVariable("cnum") Integer cateNum,
                              @RequestHeader("token") String token){
        ResultVO resultVO = shoppingCartService.updateCartNum(cateId, cateNum);
        return resultVO;
    }


    @GetMapping("/listbycids")
    @ApiImplicitParam(dataType = "String",name = "cids", value = "用户ID",required = true)
    public ResultVO list(String cids, @RequestHeader("token")String token){
        ResultVO resultVO = shoppingCartService.listShoppingCartBuCids(cids);
        return resultVO;

    }


}
