package com.hand.hjmall.controller;

import com.hand.hjmall.dto.CommonResult;
import com.hand.hjmall.model.UmsAdmin;
import com.hand.hjmall.service.UmsAdminService;
import com.hand.hjmall.utils.EmptyUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 后台用户管理
 * @date Date : 2019年03月01日, 9:51
 */
@Controller
@Api(tags = "UmsAdminController", description = "后台用户管理")
@RequestMapping("/admin")
public class UmsAdminController {
    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminController.class);
    @Autowired
    private UmsAdminService adminService;

    @ApiOperation("删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteUser(@PathVariable Long id) {
        int count = adminService.deleteUser(id);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public Object getAdminInfo(Principal principal) {
        UmsAdmin umsAdmin = null;
        if (EmptyUtil.isNull(principal)) {
            LOGGER.error("很抱歉，用户未登陆，暂未获取登陆信息");
            return new CommonResult().failed();
        }
        //返回此主体的名称。
        String userName = principal.getName();
        if (EmptyUtil.isNotEmpty(userName)) {
            umsAdmin = adminService.getAdminByUsername(userName);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("roles", new String[]{"TEST"});
        data.put("icon", umsAdmin.getIcon());
        return new CommonResult().success(data);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object getListByUserName(@RequestParam(value = "name", required = false) String name,
                                    @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                    @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> umsAdminList = adminService.list(name, pageSize, pageNum);
        return new CommonResult().pageSuccess(umsAdminList);
    }
}
