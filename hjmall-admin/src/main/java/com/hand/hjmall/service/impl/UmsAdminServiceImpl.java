package com.hand.hjmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.hand.hjmall.dto.UmsAdminParam;
import com.hand.hjmall.mapper.UmsAdminMapper;
import com.hand.hjmall.model.UmsAdmin;
import com.hand.hjmall.model.UmsAdminExample;
import com.hand.hjmall.model.UmsPermission;
import com.hand.hjmall.model.UmsRole;
import com.hand.hjmall.service.UmsAdminService;
import com.hand.hjmall.utils.EmptyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    // 日志
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private UmsAdminMapper adminMapper;

    @Override
    public UmsAdmin getAdminByUsername(String username) {
        UmsAdminExample adminExample = new UmsAdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(adminExample);
        LOGGER.info("根据用户名获取后台管理员数据为：{}", adminList);
        if (EmptyUtil.isNotEmpty(adminList)) {
            return adminList.get(0);
        }
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
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample example = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = example.createCriteria();
        if (EmptyUtil.isNotEmpty(name)) {
            criteria.andUsernameLike("%" + name + "%");
            example.or(example.createCriteria().andNickNameLike("%" + name + "%"));
        }
        return adminMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, UmsAdmin admin) {
        return 0;
    }

    @Override
    public int deleteUser(Long id) {
        return adminMapper.deleteByPrimaryKey(id);
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
