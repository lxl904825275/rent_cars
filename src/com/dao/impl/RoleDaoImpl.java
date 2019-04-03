package com.dao.impl;

import com.dao.RoleDao;
import com.entity.Role;
import com.framework.JdbcUtils;
import com.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class RoleDaoImpl implements RoleDao {
    private QueryRunner qr =  new QueryRunner(DataSourceUtil.getDruidDataSource());
    @Override
    public List<Role> qeruyAll() {
        String sql="select * from role";
        try {
            List<Role> list = qr.query(sql, new BeanListHandler<>(Role.class));
            return  list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int add(Role role) {
        String sql="insert into role (role_name) values (?)";
        try {
            int i = qr.update(sql, role.getRole_name());
            return  i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Role queryOne(String rId) {
        String sql="select * from role where r_id = ?";
        try {
            Role list = qr.query(sql, new BeanHandler<>(Role.class),rId);
            return  list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteRole(int rId){
        String sql="delete from role where r_id = ?";
        try {
            int i = qr.update(JdbcUtils.getConnection(),sql,rId);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
