package com.hand.hjmall.controller;

import com.hand.hjmall.dto.CommonResult;
import com.hand.hjmall.model.UmsPermission;
import com.hand.hjmall.model.UmsRole;
import com.hand.hjmall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : SilenceTian
 * @version V1.0
 * @Description: 后台用户角色管理
 * @date Date : 2019年02月27日, 18:46
 */
@Controller
@Api(tags = "UmsRoleController", description = "后台用户角色管理")
@RequestMapping("/role")
public class UmsRoleController {
    //日志
    private static final Logger LOGGER = LoggerFactory.getLogger(UmsRoleController.class);

    @Autowired
    private UmsRoleService roleService;

    @ApiOperation("添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Object create(@RequestBody UmsRole role) {
        int count = roleService.createRole(role);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("批量删除角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Object delete(@RequestParam("ids") List<Long> ids) {
        int count = roleService.deleteRole(ids);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list() {
        List<UmsRole> roleList = roleService.roleList();
        return new CommonResult().success(roleList);
    }

    @ApiOperation("修改角色权限")
    @RequestMapping(value = "/permission/update", method = RequestMethod.POST)
    @ResponseBody
    public Object updatePermission(@RequestParam Long roleId,
                                   @RequestParam("permissionIds") List<Long> permissionIds) {
        int count = roleService.updatePermission(roleId, permissionIds);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }

    @ApiOperation("获取相应角色权限")
    @RequestMapping(value = "/permission/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public Object getPermissionList(@PathVariable Long roleId) {
        List<UmsPermission> permissionList = roleService.getPermissionList(roleId);
        return new CommonResult().success(permissionList);
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object update(@PathVariable Long id, @RequestBody UmsRole role) {
        int count = roleService.updateRole(id, role);
        if (count > 0) {
            return new CommonResult().success(count);
        }
        return new CommonResult().failed();
    }
}
