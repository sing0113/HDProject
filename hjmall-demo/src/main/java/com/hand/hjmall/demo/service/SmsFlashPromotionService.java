package com.hand.hjmall.demo.service;

import com.hand.hjmall.demo.dto.SmsFlashPromotionDto;
import com.hand.hjmall.model.SmsFlashPromotion;

import java.util.List;

/**
 * @Description:    SmsFlashPromotionService接口
 * @CreateDate:     2019/2/27 22:00
 * @CreateUser:     吴童
 * @Version:        1.0
 */
public interface SmsFlashPromotionService {

    List<SmsFlashPromotion> listAllSmsFlashPromotion();

    int createSmsFlashPromotion(SmsFlashPromotionDto smsFlashPromotionDto);

    int updateSmsFlashPromotion(Long id, SmsFlashPromotionDto smsFlashPromotionDto);

    int deleteSmsFlashPromotion(Long id);

    List<SmsFlashPromotion> lisSmsFlashPromotion(int pageNum, int pageSize);

    SmsFlashPromotion getSmsFlashPromotion(Long id);
    
    int updateSmsFlashPromotionStatus(Long id, Integer status);


}
