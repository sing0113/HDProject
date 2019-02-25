package com.hand.hjmall.service.impl;

import com.hand.hjmall.dto.UmsAdminParam;
import com.hand.hjmall.model.UmsAdmin;
import com.hand.hjmall.model.UmsPermission;
import com.hand.hjmall.model.UmsRole;
import com.hand.hjmall.service.UmsAdminService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: UmsAdminService实现类
 * @date Date : 2019年02月21日, 20:12
 */
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        return null;
    }

    @Override
    public String refreshToken(String oldToken) {
        return null;
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return null;
    }

    @Override
    public List<UmsAdmin> list(String name, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public int update(Long id, UmsAdmin admin) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        return 0;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return null;
    }

    @Override
    public int updatePermission(Long adminId, List<Long> permissionIds) {
        return 0;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return null;
    }
}
