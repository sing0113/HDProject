package com.hand.hjmall.service;

import com.hand.hjmall.dto.UmsPermissionNode;
import com.hand.hjmall.model.UmsPermission;

import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 后台用户权限管理Service
 * @date Date : 2019年02月26日, 15:01
 */
public interface UmsPermissionService {
    /**
     * 添加权限
     */
    int create(UmsPermission permission);

    /**
     * 修改权限
     */
    int update(Long id,UmsPermission permission);

    /**
     * 批量删除权限
     */
    int delete(List<Long> ids);

    /**
     * 以层级结构返回所有权限
     */
    List<UmsPermissionNode> treeList();

    /**
     * 获取所有权限
     */
    List<UmsPermission> list();
}
