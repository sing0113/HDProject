package com.hand.hjmall.service.impl;

import com.hand.hjmall.mapper.CmsPrefrenceAreaMapper;
import com.hand.hjmall.model.CmsPrefrenceArea;
import com.hand.hjmall.model.CmsPrefrenceAreaExample;
import com.hand.hjmall.service.CmsPrefrenceAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 商品优选Service实现类
 * @date Date : 2019年03月05日, 14:36
 */
@Service
public class CmsPrefrenceAreaServiceImpl implements CmsPrefrenceAreaService {

    @Autowired
    private CmsPrefrenceAreaMapper prefrenceAreaMapper;

    /**
     * 获取所有商品优选
     *
     * @return list
     */
    @Override
    public List<CmsPrefrenceArea> listAll() {
        return prefrenceAreaMapper.selectByExample(new CmsPrefrenceAreaExample());
    }
}
