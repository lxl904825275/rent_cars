package com.dao.impl;

import com.dao.MenuDao;
import com.entity.Menu;
import com.framework.JdbcUtils;
import com.util.DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MenuDaoImpl implements MenuDao {
    private final String COLNAME = "m_id mId ,p_id  pId , menu_name menuName ,url,img ";

    private QueryRunner qr =  new QueryRunner(DataSourceUtil.getDruidDataSource());
    @Override
    public List<Menu> qeruyAll() {
        String sql="SELECT "+COLNAME+" FROM MENU";
        try {
            List<Menu> list = qr.query(sql, new BeanListHandler<>(Menu.class));
            return  list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int add(Menu menu) {
        String sql="insert into menu (menu_name,p_id,url) values (?,?,?)";
        try {
            int i = qr.update(sql,
                    menu.getMenuName(),menu.getpId(),menu.getUrl());
            return  i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Menu> RoleMenu(int uId) {
        String sql="select m_id mId ,p_id  pId , menu_name menuName ,url,img  from menu where m_id in (\n" +
                "                select m_id  from role_menu where r_id =(\n" +
                "                 select r_id  from role where r_id = (\n" +
                "                 select r_id  from user_role where u_id = ? )))";
        try {
            List<Menu> list = qr.query(sql, new BeanListHandler<>(Menu.class),uId);
            return  list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        MenuDao menuDao = new MenuDaoImpl();
        List<Menu> menus = menuDao.RoleMenu(1);
        System.out.println(menus);
    }
    @Override
    public int deleteMenu(int mId){
        String sql="delete from menu where m_id = ?";
        try {
            int i = qr.update(JdbcUtils.getConnection(),sql,mId);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public int deleteRole(int mId){
        String sql="delete from role_menu where m_id = ?";
        try {
            int i = qr.update(JdbcUtils.getConnection(),sql,mId);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    @Override
    public Menu queryOne(int mId){
        String sql="select * from menu  where m_id = ?";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,mId);
            rs = ps.executeQuery();
            while (rs.next()){
                mId = rs.getInt("m_id");
                String menuName = rs.getString("menu_name");
                int pId = rs.getInt("p_id");
                String url = rs.getString("url");
                Menu menu =  new Menu(mId,pId,menuName,url);
                return menu;
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
    public int updateMenu(Menu menu){
        String sql="update menu set menu_name = ? ,p_id = ? ,url = ? where m_id=?";
        try {
            int i = qr.update(JdbcUtils.getConnection(),sql,
                    menu.getMenuName(), menu.getpId(), menu.getUrl(),menu.getmId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
