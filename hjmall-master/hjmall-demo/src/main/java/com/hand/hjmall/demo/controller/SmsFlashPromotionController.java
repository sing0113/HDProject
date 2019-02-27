package com.hand.hjmall.demo.controller;


import com.hand.hjmall.demo.dto.CommonResult;
import com.hand.hjmall.demo.dto.SmsFlashPromotionDto;
import com.hand.hjmall.demo.service.SmsFlashPromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(value = "SmsFlashPromotionController", description = "秒杀功能接口")

@RestController
public class SmsFlashPromotionController {


    @Autowired
    private SmsFlashPromotionService smsFlashPromotionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SmsFlashPromotionController.class);

    @ApiOperation(value = "获取全部活动列表")
    @GetMapping(value = "/flash/listAll")
    public Object getSmsFlashPromotion() {
        return new CommonResult().success(smsFlashPromotionService.listAllSmsFlashPromotion());
    }


    @ApiOperation(value = "添加活动")
    @PostMapping(value = "/flash/create ")
    public Object createSmsFlashPromotion(@Validated @RequestBody SmsFlashPromotionDto smsFlashPromotionDto, BindingResult result) {
        if (result.hasErrors()) {
            return new CommonResult().validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = smsFlashPromotionService.createSmsFlashPromotion(smsFlashPromotionDto);
        if (count == 1) {
            commonResult = new CommonResult().success(smsFlashPromotionDto);
            LOGGER.debug("createSmsFlashPromotionDto success:{}", smsFlashPromotionDto);
        } else {
            commonResult = new CommonResult().failed();
            LOGGER.debug("createSmsFlashPromotionDto failed:{}", smsFlashPromotionDto);
        }
        return commonResult;
    }


    @ApiOperation(value = "更新活动")
    @PostMapping(value = "/flash/update/{id}")
    public Object updateSmsFlashPromotion(@PathVariable("id") Long id, @Validated @RequestBody SmsFlashPromotionDto smsFlashPromotionDto,BindingResult result) {
        if(result.hasErrors()){
            return new CommonResult().validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = smsFlashPromotionService.updateSmsFlashPromotion(id, smsFlashPromotionDto);
        if (count == 1) {
            commonResult = new CommonResult().success(smsFlashPromotionDto);
            LOGGER.debug("updateSmsFlashPromotionDto success:{}", smsFlashPromotionDto);
        } else {
            commonResult = new CommonResult().failed();
            LOGGER.debug("updateSmsFlashPromotionDto failed:{}", smsFlashPromotionDto);
        }
        return commonResult;
    }


    @ApiOperation(value = "删除活动")
    @GetMapping(value = "/flash/delete/{id}")
    public Object deleteSmsFlashPromotion(@PathVariable("id") Long id) {
        int count = smsFlashPromotionService.deleteSmsFlashPromotion(id);
        if (count == 1) {
            LOGGER.debug("deleteSmsFlashPromotion success :id={}", id);
            return new CommonResult().success(null);
        } else {
            LOGGER.debug("deleteSmsFlashPromotion failed :id={}", id);
            return new CommonResult().failed();
        }
    }


    @ApiOperation(value = "分页获取活动列表")
    @GetMapping(value = "/flash/list")
    public Object listSmsFlashPromotion(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                            @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize) {
        return new CommonResult().pageSuccess(smsFlashPromotionService.lisSmsFlashPromotion(pageNum, pageSize));
    }


    @ApiOperation(value = "根据编号查询活动信息")
    @GetMapping(value = "/flash/{id}")
    public Object SmsFlashPromotion(@PathVariable("id") Long id) {
        return new CommonResult().success(smsFlashPromotionService.getSmsFlashPromotion(id));
    }



}
