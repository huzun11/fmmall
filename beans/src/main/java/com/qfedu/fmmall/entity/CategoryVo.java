package com.qfedu.fmmall.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryVo {


    private Integer categoryId;


    private String categoryName;


    private Integer categoryLevel;


    private Integer parentId;


    private String categoryIcon;


    private String categorySlogan;


    private String categoryPic;

    private String categoryBgColor;

    //实现首页的类别显示
    private List<CategoryVo> categories;
    //实现首页分类商品推荐
    public List<ProductVO> products;



}
