package com.dao;

import com.entity.RoleMenu;

import java.util.List;

public interface RoleMenuDao {

    public List<RoleMenu> queryByRoleId(String rid);
    public int  addAll(RoleMenu roleMenu);
}
