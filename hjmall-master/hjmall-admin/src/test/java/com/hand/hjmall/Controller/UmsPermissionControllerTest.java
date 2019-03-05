package com.hand.hjmall.controller;

import com.hand.hjmall.dto.CommonResult;
import com.hand.hjmall.dto.UmsPermissionNode;
import com.hand.hjmall.model.UmsPermission;
import com.hand.hjmall.service.UmsPermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UmsPermissionControllerTest {

    @Autowired
    private UmsPermissionService umsPermissionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsPermissionControllerTest.class);

    /**
     * 添加权限
     */
    @Test
    public void create() {
        Object ob = null;
        UmsPermission umsPermission = new UmsPermission();
        umsPermission.setPid(7L);
        umsPermission.setName("添加图片");
        umsPermission.setValue("pms:product:addImg");
        umsPermission.setType(2);
        umsPermission.setUri("/pms/product/addImg");
        int count = umsPermissionService.create(umsPermission);
        if (count > 0) {
            ob = new CommonResult().success(count);
        } else {
            ob = new CommonResult().failed();
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("返回的结果为{}", ob);
        }
    }

    /**
     * 根据id批量删除权限
     */
    @Test
    public void delete() {
        Object ob = null;
        List<Long> ids = new ArrayList<Long>();
        ids.add(19L);
        ids.add(20L);
        int count = umsPermissionService.delete(ids);
        if (count > 0) {
            ob = new CommonResult().success(count);
        } else {
            ob = new CommonResult().failed();
        }
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("返回的结果为{}", ob);
        }
    }

    /**
     * 获取所有权限列表
     */
    @Test
    public void list() {
        Object ob = null;
        List<UmsPermission> permissionList = umsPermissionService.list();
        ob = new CommonResult().success(permissionList);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("返回的结果{}", ob);
        }

    }

    /**
     * 以层级结构返回所有权限
     */
    @Test
    public void treeList() {
        Object ob = null;
        List<UmsPermissionNode> permissionNodeList = umsPermissionService.treeList();
        ob = new CommonResult().success(permissionNodeList);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("返回的结果{}", ob);
        }
    }

    /**
     * 修改权限
     */
    @Test
    public void update() {
        Object ob=null;
        //权限ID
        Long id = 21L;
        //权限修改内容
        UmsPermission umsPermission =new UmsPermission();
        umsPermission.setName("添加图片11");
        umsPermission.setUri("/pms/product/addImg1");
        umsPermission.setPid(5L);
        umsPermission.setType(2);
       int count = umsPermissionService.update(id,umsPermission);
       if(count >0){
           ob=new CommonResult().success(count);
       }else{
           ob=new CommonResult().failed();
       }
       if(LOGGER.isInfoEnabled()){
           LOGGER.info("返回的内容{}",ob);
       }
    }
}