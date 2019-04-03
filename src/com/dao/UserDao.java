package com.dao;

import com.entity.PageBean;
import com.entity.User;
import com.entity.User1;

import java.util.List;

/**
 * @autor : WL;
 * @date : 2019/3/26 0026
 */
public interface UserDao {
    public User findOne(String username);
    public User queryById(int uId);
    public int addUser(User user);
    public int updatePassword(User user);
    public int updateState(User user);

    /**
     * 查询全部用户，以集合的形式返回
     * @return
     */
    public List<User> qeruyAll();

    /**
     * 添加user对象
     * @param user
     * @return
     */
    public int add(User1 user);

    /**
     * 通过uId查询用户
     * @param uId
     * @return 如果找对应数据返回user，否则返回null
     */
    public User1 qeruyOne(String uId);

    /**
     * 通过用户名查询
     * @param username
     * @return 如果找对应数据返回user，否则返回null
     */
    public User1 queryOneByUsername(String username);

    /**
     * 得到数据总数
     * @return
     */
    public int getCount();
    /**
     * 删除角色数据
     * @return
     */
    public int deleteRole(int uId);
    /**
     * 删除用户数据
     * @return
     */
    public int deleteUser(int uId);
    /**
     *查询某一个用户
     * @return
     */
    public User queryUserOne(String username,String role);
    /**
     * 分页显示
     * @return
     */
    public List<User1> queryPageUser(PageBean pageBean);


}
