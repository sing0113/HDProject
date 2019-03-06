package com.hand.hjmall.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.hand.hjmall.dao.UmsAdminPermissionRelationDao;
import com.hand.hjmall.dao.UmsAdminRoleRelationDao;
import com.hand.hjmall.dto.UmsAdminParam;
import com.hand.hjmall.mapper.UmsAdminLoginLogMapper;
import com.hand.hjmall.mapper.UmsAdminMapper;
import com.hand.hjmall.mapper.UmsAdminPermissionRelationMapper;
import com.hand.hjmall.mapper.UmsAdminRoleRelationMapper;
import com.hand.hjmall.model.*;
import com.hand.hjmall.service.UmsAdminService;
import com.hand.hjmall.utils.EmptyUtil;
import com.hand.hjmall.utils.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsAdminMapper adminMapper;
    // 进行密码加密
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminPermissionRelationMapper adminPermissionRelationMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    // 认证方法入口
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UmsAdminLoginLogMapper loginLogMapper;
    @Autowired
    private UmsAdminPermissionRelationDao adminPermissionRelationDao;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;

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
        UmsAdmin umsAdmin = new UmsAdmin();
        // 构造对象 进行赋值，
        BeanUtils.copyProperties(umsAdminParam, umsAdmin);
        umsAdmin.setCreateTime(new Date());
        // 帐号启用状态,1代表已启用
        umsAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(umsAdmin.getUsername());
        List<UmsAdmin> umsAdminList = adminMapper.selectByExample(example);
        if (EmptyUtil.isNotEmpty(umsAdminList)) {
            return null;
        }
        //将密码进行加密操作
        String md5Password = passwordEncoder.encodePassword(umsAdmin.getPassword(), null);
        umsAdmin.setPassword(md5Password);
        adminMapper.insert(umsAdmin);
        return umsAdmin;
    }

    /**
     * Spring Security身份认证
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @Override
    public String login(String username, String password) {
        String token = null;
        if (EmptyUtil.isNotEmpty(username) && EmptyUtil.isNotEmpty(password)) {
            // 对密码进行加密
            String password1 = passwordEncoder.encodePassword(password, null);
            // 密码需要进行加密 --> .username和password被获得后封装到一个UsernamePasswordAuthenticationToken（Authentication接口的实例）的实例中
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password1);
            LOGGER.info("authenticationToken值为：{}", new Gson().toJson(authenticationToken));
            try {
                if (EmptyUtil.isNull(authenticationToken) || EmptyUtil.isEmpty(authenticationToken.getAuthorities())) {
                    LOGGER.warn("authenticationToken无法进行验证，请确认数据！");
                    return token;
                }
                // 2.这个token被传递给AuthenticationManager进行验证
                // 3.成功认证后AuthenticationManager将返回一个得到完整填充的Authentication实例
                Authentication authentication = authenticationManager.authenticate(authenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                LOGGER.info("成功认证后获取的实例为：{}", new Gson().toJson(authentication));
                // 4..通过调用SecurityContextHolder.getContext().setAuthentication(...)，
                // 参数传递authentication对象，来建立安全上下文（security context）
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                LOGGER.info("根据用户获取数据为：{}", new Gson().toJson(userDetails));
                // 根据用户信息生成token
                token = jwtTokenUtil.generateToken(userDetails);
                LOGGER.info("根据用户信息生成token为：{}", new Gson().toJson(token));
                updateLoginTimeByUsername(username);
                insertLoginLog(username);
            } catch (AuthenticationException e) {
                LOGGER.warn("登录异常:{}", e.getMessage());
            }
        }
        return token;
    }


    @Override
    public String refreshToken(String oldToken) {
        if (EmptyUtil.isNotEmpty(oldToken)) {
            String token = oldToken.substring(tokenHead.length());
            if (jwtTokenUtil.canRefresh(token)) {
                return jwtTokenUtil.refreshToken(token);
            }
        }
        return null;
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsAdmin> list(String name, Integer pageSize, Integer pageNum) {
        // 设置分页
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
        admin.setId(id);
        return adminMapper.updateByPrimaryKey(admin);
    }

    @Override
    public int deleteUser(Long id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        UmsAdminRoleRelationExample adminRoleRelationExample = new UmsAdminRoleRelationExample();
        adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);
        //建立新关系
        if (EmptyUtil.isNotEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                UmsAdminRoleRelation roleRelation = new UmsAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationDao.insertList(list);
        }
        return count;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public int updatePermission(Long adminId, List<Long> permissionIds) {
        //删除原所有权限关系
        UmsAdminPermissionRelationExample relationExample = new UmsAdminPermissionRelationExample();
        relationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminPermissionRelationMapper.deleteByExample(relationExample);
        //获取用户所有角色权限
        List<UmsPermission> permissionList = adminRoleRelationDao.getRolePermissionList(adminId);
        List<Long> rolePermissionList = permissionList.stream().map(UmsPermission::getId).collect(Collectors.toList());
        if (EmptyUtil.isNotEmpty(permissionIds)) {
            List<UmsAdminPermissionRelation> relationList = new ArrayList<>();
            //筛选出+权限
            List<Long> addPermissionIdList = permissionIds.stream().filter(permissionId -> !rolePermissionList.contains(permissionId)).collect(Collectors.toList());
            //筛选出-权限
            List<Long> subPermissionIdList = rolePermissionList.stream().filter(permissionId -> !permissionIds.contains(permissionId)).collect(Collectors.toList());
            //插入+-权限关系
            relationList.addAll(convert(adminId, 1, addPermissionIdList));
            relationList.addAll(convert(adminId, -1, subPermissionIdList));
            return adminPermissionRelationDao.insertList(relationList);
        }
        return 0;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }

    /**
     * 将+-权限关系转化为对象
     */
    private List<UmsAdminPermissionRelation> convert(Long adminId, Integer type, List<Long> permissionIdList) {
        List<UmsAdminPermissionRelation> relationList = permissionIdList.stream().map(permissionId -> {
            UmsAdminPermissionRelation relation = new UmsAdminPermissionRelation();
            relation.setAdminId(adminId);
            relation.setType(type);
            relation.setPermissionId(permissionId);
            return relation;
        }).collect(Collectors.toList());
        return relationList;
    }

    /**
     * 根据用户名修改登录时间
     *
     * @param username 用户名
     */
    private void updateLoginTimeByUsername(String username) {
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setLoginTime(new Date());
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        adminMapper.updateByExampleSelective(umsAdmin, example);
    }

    /**
     * 添加登陆记录
     *
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        // 根据用户名获取后台管理员
        UmsAdmin admin = getAdminByUsername(username);
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }
}
