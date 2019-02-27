package com.hand.hjmall.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hjmall.demo.dto.SmsFlashPromotionDto;
import com.hand.hjmall.demo.service.SmsFlashPromotionService;
import com.hand.hjmall.mapper.*;
import com.hand.hjmall.model.SmsFlashPromotion;
import com.hand.hjmall.model.SmsFlashPromotionExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description:    SmsFlashPromotionService实现类
 * @CreateDate:     2019/2/27 22:00
 * @CreateUser:     吴童
 * @Version:        1.0
 */
@Service
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {

//    @Autowired
//    private SmsCouponHistoryMapper smsCouponHistoryMapper;
//
//    @Autowired
//    private SmsCouponMapper smsCouponMapper;
//
//    @Autowired
//    private SmsCouponProductCategoryRelationMapper smsCouponProductCategoryRelationMapper;
//
//    @Autowired
//    private SmsCouponProductRelationMapper smsCouponProductRelationMapper ;

    @Autowired
    private SmsFlashPromotionMapper smsFlashPromotionMapper;

//    @Autowired
//    private SmsFlashPromotionLog smsFlashPromotionLog;
//
//    @Autowired
//    private SmsFlashPromotionProductRelationMapper smsFlashPromotionProductRelationMapper;
//
//    @Autowired
//    private SmsFlashPromotionSessionMapper smsFlashPromotionSessionMapper;
//
//    @Autowired
//    private SmsHomeAdvertiseMapper smsHomeAdvertiseMapper;
//
//    @Autowired
//    private SmsHomeBrandMapper smsHomeBrandMapper;
//
//    @Autowired
//    private SmsHomeNewProductMapper smsHomeNewProductMapper;
//
//    @Autowired
//    private SmsHomeRecommendProductMapper smsHomeRecommendProductMapper;
//
//    @Autowired
//    private SmsHomeRecommendSubjectMapper smsHomeRecommendSubjectMapper;


    @Override
    public List<SmsFlashPromotion> listAllSmsFlashPromotion() {
        return smsFlashPromotionMapper.selectByExample(new SmsFlashPromotionExample());
    }

    @Override
    public int createSmsFlashPromotion(SmsFlashPromotionDto smsFlashPromotionDto) {
        SmsFlashPromotion smsFlashPromotion = new SmsFlashPromotion();
        BeanUtils.copyProperties(smsFlashPromotionDto,smsFlashPromotion);
        return smsFlashPromotionMapper.insertSelective(smsFlashPromotion);
    }

    @Override
    public int updateSmsFlashPromotion(Long id, SmsFlashPromotionDto smsFlashPromotionDto) {
        SmsFlashPromotion smsFlashPromotion = new SmsFlashPromotion();
        BeanUtils.copyProperties(smsFlashPromotionDto,smsFlashPromotion);
        smsFlashPromotion.setId(id);
        return smsFlashPromotionMapper.updateByPrimaryKeySelective(smsFlashPromotion);
    }

    @Override
    public int deleteSmsFlashPromotion(Long id) {
        return smsFlashPromotionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<SmsFlashPromotion> lisSmsFlashPromotion(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return smsFlashPromotionMapper.selectByExample(new SmsFlashPromotionExample());
    }

    @Override
    public SmsFlashPromotion getSmsFlashPromotion(Long id) {
        return smsFlashPromotionMapper.selectByPrimaryKey(id);
    }
}
