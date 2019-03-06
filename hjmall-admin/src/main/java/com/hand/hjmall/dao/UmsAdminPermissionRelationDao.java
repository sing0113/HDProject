package com.hand.hjmall.dao;

import com.hand.hjmall.model.UmsAdminPermissionRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 用户权限自定义Dao
 * @date Date : 2019年03月04日, 16:15
 */
public interface UmsAdminPermissionRelationDao {
    int insertList(@Param("list") List<UmsAdminPermissionRelation> list);
}
