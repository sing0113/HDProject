package com.hand.hjmall.demo.service;

import com.hand.hjmall.demo.dto.PmsBrandDto;
import com.hand.hjmall.model.PmsBrand;

import java.util.List;

/**
* @Description:    DemoService接口
* @CreateDate:     2019/2/21 16:57
* @CreateUser:     SilenceTian
* @Version:        1.0
*/
public interface DemoService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrandDto pmsBrandDto);

    int updateBrand(Long id, PmsBrandDto pmsBrandDto);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
