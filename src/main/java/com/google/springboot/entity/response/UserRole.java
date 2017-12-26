package com.google.springboot.entity.response;

import java.io.Serializable;

public class UserRole implements Serializable {
    public static final long serialVersionUID = 1L;

    private long roleId;
    private String roleName;
    private String roleShortName;

    public long getRoleId() {
        return roleId;
    }

    public void setRoleId(long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleShortName() {
        return roleShortName;
    }

    public void setRoleShortName(String roleShortName) {
        this.roleShortName = roleShortName;
    }
}
