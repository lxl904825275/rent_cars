package com.dao.impl;

import com.dao.UserRoleDao;
import com.entity.UserRole;
import com.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserRoleDaoImpl implements UserRoleDao {
    private QueryRunner qr =  new QueryRunner(DataSourceUtil.getDruidDataSource());
    @Override
    public List<UserRole> qeruyAll() {
        String sql="select * from user_role";
        try {
            List<UserRole> list = qr.query(sql, new BeanListHandler<>(UserRole.class));
            return  list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addUserRole(UserRole userRole) {
        String sql="insert into user_role (u_id,r_id) values (?,?)";
        try {
            int i = qr.update(sql,userRole.getU_id(),userRole.getR_id());
            return  i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
