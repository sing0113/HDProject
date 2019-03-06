package com.hand.hjmall.service;

import com.hand.hjmall.model.CmsPrefrenceArea;

import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 商品优选Service
 * @date Date : 2019年03月05日, 14:35
 */
public interface CmsPrefrenceAreaService {

    /**
     * 获取所有商品优选
     *
     * @return list
     */
    List<CmsPrefrenceArea> listAll();
}
