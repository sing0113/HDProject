package com.hand.hjmall.service.impl;

import com.hand.hjmall.dao.UmsRolePermissionRelationDao;
import com.hand.hjmall.mapper.UmsRoleMapper;
import com.hand.hjmall.mapper.UmsRolePermissionRelationMapper;
import com.hand.hjmall.model.*;
import com.hand.hjmall.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 后台角色管理Service实现类
 * @date Date : 2019年02月27日, 19:02
 */
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Autowired
    private UmsRoleMapper umsRoleMapper;
    @Autowired
    private UmsRolePermissionRelationMapper rolePermissionRelationMapper;
    @Autowired
    private UmsRolePermissionRelationDao rolePermissionRelationDao;

    @Override
        public int createRole(UmsRole role) {
        role.setStatus(1);
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return  umsRoleMapper.insert(role);
    }

    @Override
    public int updateRole(Long id, UmsRole role) {
        role.setId(id);
        return umsRoleMapper.updateByPrimaryKey(role);
    }

    @Override
    public int deleteRole(List<Long> ids) {
        UmsRoleExample example = new UmsRoleExample();
        example.createCriteria().andIdIn(ids);
        return umsRoleMapper.deleteByExample(example);
    }

    @Override
    public List<UmsPermission> getPermissionList(Long roleId) {
        return rolePermissionRelationDao.getPermissionList(roleId);
    }

    @Override
    public int updatePermission(Long roleId, List<Long> permissionIds) {
        //先删除原有关系
        UmsRolePermissionRelationExample example = new UmsRolePermissionRelationExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        rolePermissionRelationMapper.deleteByExample(example);
        //批量插入新关系
        List<UmsRolePermissionRelation> relationList = new ArrayList<>();
        for (Long permissionId : permissionIds) {
            UmsRolePermissionRelation relation = new UmsRolePermissionRelation();
            relation.setRoleId(roleId);
            relation.setPermissionId(permissionId);
            relationList.add(relation);
        }
        return rolePermissionRelationDao.insertList(relationList);
    }

    @Override
    public List<UmsRole> roleList() {
        return umsRoleMapper.selectByExample(new UmsRoleExample());
    }
}
