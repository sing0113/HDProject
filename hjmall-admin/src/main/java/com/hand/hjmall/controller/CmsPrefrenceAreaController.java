package com.hand.hjmall.controller;

import com.hand.hjmall.dto.CommonResult;
import com.hand.hjmall.model.CmsPrefrenceArea;
import com.hand.hjmall.service.CmsPrefrenceAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 商品优选管理Controller
 * @date Date : 2019年03月05日, 14:30
 */
@Controller
@Api(tags = "CmsPrefrenceAreaController", description = "商品优选管理")
@RequestMapping("/prefrenceArea")
public class CmsPrefrenceAreaController {
    @Autowired
    private CmsPrefrenceAreaService prefrenceAreaService;

    @ApiOperation(value = "商品优选管理Controller")
    @RequestMapping(value = "/listAll",method = RequestMethod.GET)
    @ResponseBody
    public Object listAll(){
        List<CmsPrefrenceArea> prefrenceAreaList = prefrenceAreaService.listAll();
        return new CommonResult().success(prefrenceAreaList);
    }
}
