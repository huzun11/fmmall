package com.qfedu.fmmall.controller;

import com.qfedu.fmmall.service.ProductCommontsService;
import com.qfedu.fmmall.service.ProductService;
import com.qfedu.fmmall.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@Api(value = "提供商品信息相关的接口",tags = "商品管理")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCommontsService productCommontsService;

    @ApiOperation("商品基本信息查询接口")
    @GetMapping("/detail-info/{pid}")
    public ResultVO getProductBasicInfo(@PathVariable("pid") String pid){
        return productService.getProductBasicInfo(pid);
    }

    @ApiOperation("商品参数信息查询接口")
    @GetMapping("/detail-params/{pid}")
    public ResultVO getProductParams(@PathVariable("pid") String pid){
        return productService.getProductParamsById(pid);
    }

    @ApiOperation("商品评论信息查询接口")
    @GetMapping("/detail-commonts/{pid}")
        @ApiImplicitParams({
        @ApiImplicitParam(dataType = "int",name = "pageNum", value = "当前页码",required = true),
        @ApiImplicitParam(dataType = "int",name = "limit", value = "每页显示条数",required = true)
    })
    public ResultVO getProductCommonts(@PathVariable("pid") String pid,int pageNum,int limit){
        return productCommontsService.listCommontsByProductId(pid,pageNum,limit);
    }

    @ApiOperation("商品评价统计查询接口")
    @GetMapping("/detail-commontscount/{pid}")
    public ResultVO getProductCommontsCount(@PathVariable("pid") String pid){
        return productCommontsService.getCommentsCountByProductId(pid);
    }

}
