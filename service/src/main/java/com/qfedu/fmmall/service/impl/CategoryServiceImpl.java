package com.qfedu.fmmall.service.impl;

import com.qfedu.fmmall.dao.CategoryMapper;
import com.qfedu.fmmall.entity.CategoryVo;
import com.qfedu.fmmall.service.CategoryService;
import com.qfedu.fmmall.vo.ResStatus;
import com.qfedu.fmmall.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    /**
     * 查询分类列表(包含三个级别的分类)
     * @return
     */
    @Override
    public ResultVO listCategories() {

        List<CategoryVo> categoryVos = categoryMapper.selectAllCategories();
        ResultVO resultVO = new ResultVO(ResStatus.OK,"success", categoryVos);
        return resultVO;
    }


    /**
     * 查询所有一级分类，同时查询当前一级分类下销量最高的6个商品
     * @return
     */

    @Override
    public ResultVO listFirstLevelCategories() {
        List<CategoryVo> categoryVos = categoryMapper.selectFirstLevelCategories();

        return new ResultVO(ResStatus.OK,"success",categoryVos);
    }


}
