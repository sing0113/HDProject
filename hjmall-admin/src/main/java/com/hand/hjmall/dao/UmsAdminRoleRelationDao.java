package com.hand.hjmall.dao;

import com.hand.hjmall.model.UmsAdminRoleRelation;
import com.hand.hjmall.model.UmsPermission;
import com.hand.hjmall.model.UmsRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 后台用户与角色管理自定义Dao
 * @date Date : 2019年03月04日, 16:22
 */
public interface UmsAdminRoleRelationDao {
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用于所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有角色权限
     */
    List<UmsPermission> getRolePermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
