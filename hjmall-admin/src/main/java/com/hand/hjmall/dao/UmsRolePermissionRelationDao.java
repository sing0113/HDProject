package com.hand.hjmall.dao;

import com.hand.hjmall.model.UmsPermission;
import com.hand.hjmall.model.UmsRolePermissionRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 后台用户角色管理自定义Dao
 * @date Date : 2019年02月27日, 19:04
 */
public interface UmsRolePermissionRelationDao {
    /**
     * 批量插入角色和权限关系
     */
    int insertList(@Param("list") List<UmsRolePermissionRelation> list);

    /**
     * 根据角色获取权限
     */
    List<UmsPermission> getPermissionList(@Param("roleId") Long roleId);
}
