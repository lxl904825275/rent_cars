package com.dao.impl;

import com.dao.UserDao;
import com.entity.PageBean;
import com.entity.User;
import com.entity.User1;
import com.framework.JdbcUtil;
import com.framework.JdbcUtils;
import com.mysql.jdbc.PreparedStatement;

import com.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor : WL;
 * @date : 2019/3/26 0026
 */
public class UserDaoImpl implements UserDao {
    private final String COLNAME=" u.u_id  uid,  u.username  username ,u.password password, r.role_name rolename ";
    private QueryRunner queryRunner = new QueryRunner(DataSourceUtil.getDruidDataSource());
    @Override
    public User findOne(String username) {
        String sql = "select * from user where username=?";
        try {
            User query = queryRunner.query(JdbcUtil.getConnection(), sql, new BeanHandler<>(User.class), username);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User queryById(int uId) {
        String sql = "select * from user where u_id=?";
        try {
            User query = queryRunner.query(JdbcUtil.getConnection(), sql, new BeanHandler<>(User.class), uId);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addUser(User user) {
        String sql = "insert into user(username,password,user,user_state) values (?,?,'用户','允许')";
        try {
            int i = queryRunner.update(JdbcUtil.getConnection(), sql, user.getUsername(), user.getPassword());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updatePassword(User user) {
        String sql = "update user set password=? where user_id=?";
        try {
            int i = queryRunner.update(JdbcUtil.getConnection(), sql, user.getPassword(), user.getUserId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateState(User user) {
        String sql = "update user set user_state=? where user_id=?";
        try {
            int i = queryRunner.update(JdbcUtil.getConnection(), sql, user.getUserstate(),user.getUserId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public List<User> qeruyAll() {
        String sql="SELECT "+COLNAME+" FROM USER U left join USER_ROLE UR on U.U_ID = UR.U_ID left join ROLE R " +
                " on UR.R_ID = R.R_ID";
        try {
            List<User> list = queryRunner.query(sql, new BeanListHandler<>(User.class));
            return  list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public int add(User1 user) {
        String sql="insert into user (username,password) values (?,?)";
        try {
            int i = queryRunner.update(sql,user.getUsername(),user.getPassword());
            System.out.println(i+"========");
            return  i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  0;
    }

    @Override
    public User1 qeruyOne(String uId) {
        String sql="select u_id  uid ,username ,password from user where u_id = ?";
        try {
            User1 user = queryRunner.query(sql, new BeanHandler<>(User1.class),uId);
            return  user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;
    }

    @Override
    public User1 queryOneByUsername(String username) {
        String sql="select u_id  uId ,username ,password from user where username = ?";
        try {
            User1 user = queryRunner.query(JdbcUtil.getConnection(),sql, new BeanHandler<>(User1.class),username);
            return  user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        String sql="select count(*) count from user ";
        java.sql.PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
             ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int count = rs.getInt("count");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return 0;
    }

    public User queryOne(String username){

        String sql="select * from user where username = ?";
        try {
            User user = queryRunner.query(sql, new BeanHandler<>(User.class),username);
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int deleteUser(int uId) {
        String sql="delete from user where u_id = ?";
        try {
            int i = queryRunner.update(JdbcUtils.getConnection(),sql,uId);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public int deleteRole(int uId){
        String sql="delete from user_role where u_id = ?";
        try {
            int i = queryRunner.update(JdbcUtils.getConnection(),sql,uId);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public User queryUserOne(String username,String role) {
        String sql="select u_id,username,password,user,user_state from user where username=? and role=?";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = (PreparedStatement) connection.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,role);
            rs = ps.executeQuery();
            while (rs.next()){
                int uId = rs.getInt("u_id");
                username = rs.getString("username");
                String password = rs.getString("password");
                role = rs.getString("user");
                String roleState = rs.getString("user_state");
                User user =  new User(uId,username,password,role,roleState);
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs!=null)
                    rs.close();
                if(ps!=null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtils.close();
        }
        return null;
    }

    @Override
    public List<User1> queryPageUser(PageBean pageBean) {
        List<User1> list = new ArrayList<>();

        String sql="select user.u_id,username,password,role.role_name,user_state from user left join user_role  on user.u_id = user_role.u_id left join role on user_role.r_id = role.r_id order by u_id limit ?,?";
        java.sql.PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBean.getIndex());
            ps.setInt(2,pageBean.getPageCount());
            rs = ps.executeQuery();
            while (rs.next()){
                int uId = rs.getInt("u_id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String roleName = rs.getString("role_name");
                String roleState = rs.getString("user_state");
                User1 user =  new User1(uId,username,password,roleName,roleState);
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


}
