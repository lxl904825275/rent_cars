package com.dao;

import com.entity.UserRole;

import java.util.List;

public interface UserRoleDao {
    /**
     * 查询全部的用户角色信息
     * @return
     */
    public List<UserRole> qeruyAll();

    /**
     * 添加用户角色信息
     * @param userRole
     * @return
     */
    public int addUserRole(UserRole userRole);
}
