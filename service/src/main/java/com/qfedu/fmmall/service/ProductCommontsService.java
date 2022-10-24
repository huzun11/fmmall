package com.qfedu.fmmall.service;

import com.qfedu.fmmall.vo.ResultVO;

public interface ProductCommontsService {

    /**
     * 根据商品id实现评论的分页查询
     * @param productId
     * @param pageNum
     * @param limit
     * @return
     */
    public ResultVO listCommontsByProductId(String productId,int pageNum,int limit);

    /**
     * 根据商品id统计当前商品的评论信息
     * @param productId
     * @return
     */

    public ResultVO getCommentsCountByProductId(String productId);
}
