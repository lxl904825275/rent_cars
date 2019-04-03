package com.dao;

import com.entity.Menu;

import java.util.List;

public interface MenuDao {
    /**
     * 查询全部菜单
     * @return
     */
    public List<Menu> qeruyAll();

    /**
     * 添加菜单
     * @param menu
     * @return
     */
    public int add(Menu menu);

    /**
     *
     * @param uId
     * @return
     */
    public List<Menu> RoleMenu(int uId);
    /**
     * 删除菜单角色数据
     * @return
     */
    public int deleteRole(int mId);
    /**
     * 删除菜单数据
     * @return
     */
    public int deleteMenu(int mId);
    /**
     * 查询菜单
     * @return
     */
    public Menu queryOne(int mId);
    /**
     * 修改菜单
     * @return
     */
    public int updateMenu(Menu menu);

}
