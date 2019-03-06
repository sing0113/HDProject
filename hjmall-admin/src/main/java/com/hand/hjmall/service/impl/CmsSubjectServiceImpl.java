package com.hand.hjmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hjmall.mapper.CmsSubjectMapper;
import com.hand.hjmall.model.CmsSubject;
import com.hand.hjmall.model.CmsSubjectExample;
import com.hand.hjmall.service.CmsSubjectService;
import com.hand.hjmall.utils.EmptyUtil;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 商品专题Service实现类
 * @date Date : 2019年03月05日, 13:49
 */
@Service
public class CmsSubjectServiceImpl implements CmsSubjectService {

    @Autowired
    private CmsSubjectMapper subjectMapper;

    /**
     * 根据专题名称分页获取专题
     *
     * @param keyword  专题名称
     * @param pageNum  分页数
     * @param pageSize 分页
     * @return List
     */
    @Override
    public List<CmsSubject> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        CmsSubjectExample subjectExample = new CmsSubjectExample();
        CmsSubjectExample.Criteria criteria = subjectExample.createCriteria();
        if (EmptyUtil.isNotEmpty(keyword)) {
            criteria.andTitleLike("%" + keyword + "%");
        }
        return subjectMapper.selectByExample(subjectExample);
    }

    /**
     * 查询所有专题
     *
     * @return list
     */
    @Override
    public List<CmsSubject> listAll() {
        return subjectMapper.selectByExample(new CmsSubjectExample());
    }
}
