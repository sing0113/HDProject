package com.hand.hjmall.controller;


import com.hand.hjmall.dto.CommonResult;
import com.hand.hjmall.model.UmsPermission;
import com.hand.hjmall.model.UmsRole;
import com.hand.hjmall.model.UmsRoleExample;
import com.hand.hjmall.service.UmsRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UmsRoleControllerTest {

    @Autowired
    private UmsRoleService umsRoleService;

    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsRoleController.class);

    /**
     * 功能描述：添加角色
     * (功能详情描述)
     * @param
     * @author    cwj
     * @date  2019/3/3 17:20
     */

    @Test
    public void create() {
        Object ob = null;
        UmsRole umsRole = new UmsRole();
        umsRole.setName("普通用戶");
        umsRole.setDescription("普通用戶");
        umsRole.setAdminCount(0);
        umsRole.setCreateTime(new Date());
        umsRole.setStatus(1);
        umsRole.setSort(0);
        int count = umsRoleService.createRole(umsRole);
        if (count > 0) {
            ob = new CommonResult().success(count);
        } else {
            ob = new CommonResult().failed();
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("返回结果为{}", ob);
        }

    }

    @Test
    public void delete() {
        Object ob = null;
        List<Long> listid = new ArrayList();
        listid.add(5L);
        listid.add(7L);
        int count = umsRoleService.deleteRole(listid);
        if (count > 0) {
            ob = new CommonResult().success(count);
        } else {
            ob = new CommonResult().failed();
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("返回的结果为{}", ob);
        }
    }

    @Test
    public void list() {
        Object ob = null;
        List<UmsRole> roleList = umsRoleService.roleList();
        ob = new CommonResult().success(roleList);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("返回的结果{}", ob);
        }
    }

    /**
     * 修改权限
     */
    @Test
    public void updatePermission() {
        Object ob = null;
        //角色
        Long roleID = 4L;
        //角色权限
        List<Long> permissionIds = new ArrayList<>();
        permissionIds.add(6L);
        permissionIds.add(15L);
        permissionIds.add(16L);
        int count = umsRoleService.updatePermission(roleID, permissionIds);
        if (count > 0) {
            ob = new CommonResult().success(count);
        } else {
            ob = new CommonResult().failed();
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("返回內容为{}", ob);
        }
    }

    /**
     * 获取相应角色权限
     */
    @Test
    public void getPermissionList() {
        Object ob = null;
        //角色
        Long roleId = 3L;
        List<UmsPermission> umsPermissions = umsRoleService.getPermissionList(roleId);
        ob = new CommonResult().success(umsPermissions);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("返回的内容为{}", ob);
        }
    }

    /**
     * 修改角色
     */
   @Test
    public void update() {
       Object ob=null;
        //角色ID
        Long roleId=5L;
        UmsRole umsRole = new UmsRole();
        umsRole.setAdminCount(0);
        umsRole.setCreateTime(new Date());
        umsRole.setName("超级管理员");
        umsRole.setDescription("超级管理员");
        umsRole.setSort(0);
        umsRole.setStatus(1);
        int count = umsRoleService.updateRole(roleId,umsRole);
        if(count>0){
            ob = new CommonResult().success(count);
        }else{
            ob = new CommonResult().failed();
        }
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("返回的结果{}",ob);
        }
    }
}