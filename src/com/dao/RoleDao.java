package com.dao;

import com.entity.Role;

import java.util.List;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.cn;

public interface RoleDao {
    /**
     * 查询全部的角色
     * @return
     */
    public List<Role> qeruyAll();

    /**
     * 添加角色
     * @param role
     * @return
     */
    public int add(Role role);

    /**
     * 通过角色ID查询角色
     * @param rId
     * @return
     */
    public Role queryOne(String rId);

    public int deleteRole(int rId);
}
