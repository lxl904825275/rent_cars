package com.dao.impl;

import com.dao.RoleMenuDao;
import com.entity.RoleMenu;
import com.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class RoleMenuDaoImpl implements RoleMenuDao {
    private QueryRunner qr =  new QueryRunner(DataSourceUtil.getDruidDataSource());
    @Override
    public List<RoleMenu> queryByRoleId(String rid) {
        String sql="select m_id mid from role_menu  where r_id=?";
        try {
            List<RoleMenu> list = qr.query(sql, new BeanListHandler<>(RoleMenu.class),rid);
            return  list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addAll(RoleMenu roleMenu) {
        String sql="insert into role_menu (r_id,m_id) values (?,?)";
        try {
            int i = qr.update(sql,roleMenu.getR_id(),roleMenu.getM_id());
            return  i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
