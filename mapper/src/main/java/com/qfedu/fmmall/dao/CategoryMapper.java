package com.qfedu.fmmall.dao;

import com.qfedu.fmmall.entity.Category;
import com.qfedu.fmmall.entity.CategoryVo;
import com.qfedu.fmmall.general.GeneralDAO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryMapper extends GeneralDAO<Category> {


    //第一种，连接查询
    public List<CategoryVo> selectAllCategories();

    //第二种，子查询，根据parentId查询子分类
    public List<CategoryVo> selectAllCategories2(int parentId);


    //3.查询一级类别
    public List<CategoryVo> selectFirstLevelCategories();

}