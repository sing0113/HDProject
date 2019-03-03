package com.hand.hjmall.service;

import com.hand.hjmall.model.UmsPermission;
import com.hand.hjmall.model.UmsRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 后台角色管理Service
 * @date Date : 2019年02月27日, 18:57
 */
public interface UmsRoleService {

    /**
     * 添加角色
     */
    int createRole(UmsRole role);

    /**
     * 修改角色信息
     */
    int updateRole(Long id,UmsRole role);

    /**
     * 批量删除角色
     */
    int deleteRole(List<Long> ids);

    /**
     * 获取指定角色权限
     */
    List<UmsPermission> getPermissionList(Long roleId);

    /**
     * 修改指定角色的权限
     */
    @Transactional
    int updatePermission(Long roleId, List<Long> permissionIds);

    /**
     * 获取角色列表
     */
    List<UmsRole> roleList();
}
