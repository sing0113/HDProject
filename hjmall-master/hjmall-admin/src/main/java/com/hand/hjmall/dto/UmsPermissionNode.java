package com.hand.hjmall.dto;

import com.hand.hjmall.model.UmsPermission;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
* @Description:    层级结构
* @CreateDate:     2019/2/26 15:04
* @CreateUser:     SilenceTian
* @Version:        1.0
*/
public class UmsPermissionNode extends UmsPermission {
    @Getter
    @Setter
    private List<UmsPermissionNode> children;
}
