package com.hand.hjmall.service;

import com.hand.hjmall.model.CmsSubject;

import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 商品专题Service
 * @date Date : 2019年03月05日, 13:49
 */
public interface CmsSubjectService {
    /**
     * 根据专题名称分页获取专题
     *
     * @param keyword  专题名称
     * @param pageNum  分页数
     * @param pageSize 分页
     * @return List
     */
    List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 查询所有专题
     * @return list
     */
    List<CmsSubject> listAll();
}
