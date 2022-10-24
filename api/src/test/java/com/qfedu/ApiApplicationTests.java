package com.qfedu;

import com.qfedu.fmmall.dao.CategoryMapper;
import com.qfedu.fmmall.dao.ProductCommentsMapper;
import com.qfedu.fmmall.dao.ProductMapper;
import com.qfedu.fmmall.entity.CategoryVo;
import com.qfedu.fmmall.entity.ProductCommentsVO;
import com.qfedu.fmmall.entity.ProductVO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
class ApiApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductCommentsMapper productCommentsMapper;

    @Test
    void contextLoads() {
        List<CategoryVo> categoryVos =  categoryMapper.selectAllCategories2(0);
        for (CategoryVo c1 :categoryVos) {
            System.out.println("c1 = " + c1);
            for (CategoryVo c2 : c1.getCategories()) {
                System.out.println("c2 = " + c2);
                for (CategoryVo c3 : c2.getCategories()) {
                    System.out.println("c3 = " + c3);
                }
            }

        }
    }

//    @Test
//    public void  testRecommand() {
//        List<ProductCommentsVO> productCommentsVOS = productCommentsMapper.listCommontsByProductId("3");
//
//        for (ProductCommentsVO p: productCommentsVOS
//             ) {
//            System.out.println("p = " + p);
//        }



//    }





}
