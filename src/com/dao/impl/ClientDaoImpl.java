package com.dao.impl;

import com.dao.ClientDao;
import com.entity.Client;
import com.entity.PageBean;
import com.entity.User;
import com.fr.function.SQL;
import com.framework.JdbcUtil;
import com.framework.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ClientDaoImpl implements ClientDao {
    private QueryRunner qr=new QueryRunner();

   private final static String SQLCOL= "client_id clientId ,`name`, `sex`, `age`, `id_number`  idNumber, `email`, `tel`, `drive_number`  driveNumber, `drive_type` driveType , `address`, `integral`";

    @Override
    public List<Client> findAllClient() {
        List<Client>list = new ArrayList<>();
        String sql = "select "+SQLCOL+" from client";
        Connection conn = JdbcUtils.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String idNumber = rs.getString("idNumber");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String driveNumber = rs.getString("driveNumber");
                String driveType = rs.getString("driveType");
                String address = rs.getString("address");
                Client client = new Client(name, sex, age, idNumber, email,tel, driveNumber, driveType, address);
                list.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    @Override
    public Client findByIdNumber(String str) {
        String sql = "select "+ SQLCOL +" from client where id_number=?";
        Connection conn = JdbcUtils.getConnection();
        Client client = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,str);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String idNumber = rs.getString("idNumber");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String driveNumber = rs.getString("driveNumber");
                String driveType = rs.getString("driveType");
                String address = rs.getString("address");
                client = new Client(name, sex, age, idNumber, email, tel, driveNumber, driveType, address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
    @Override
    public Client findOne(String username) {
        String sql="select "+SQLCOL+" from client where client_id=(select client_id from user where username=?)";
        try {
            Client query = qr.query(JdbcUtil.getConnection(), sql, new BeanHandler<>(User.class), username);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Client> findAll() {
        List<Client> list = new ArrayList<>();

        String sql="select * from client order by age";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int clientId = rs.getInt("client_id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String idNumber = rs.getString("id_number");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String driveNumber = rs.getString("drive_number");
                String driveType = rs.getString("drive_type");
                String address = rs.getString("address");
                int integral = rs.getInt("integral");
                Client client =  new Client(clientId,name,sex,age,idNumber,email,
                        tel,driveNumber,driveType,address,integral);
                list.add(client);
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
        return list;
    }

    @Override
    public int getConut() {
        String sql="select count(1) count from client ";
        PreparedStatement ps=null;
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

    @Override
    public List<Client> queryPageList(PageBean pageBean) {
        List<Client> list = new ArrayList<>();
        String sql="select * from client order by age limit ?,?";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBean.getIndex());
            ps.setInt(2,pageBean.getPageCount());
            rs = ps.executeQuery();
            while (rs.next()){
                int clientId = rs.getInt("client_id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String idNumber = rs.getString("id_number");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String driveNumber = rs.getString("drive_number");
                String driveType = rs.getString("drive_type");
                String address = rs.getString("address");
                int integral = rs.getInt("integral");
                Client client =  new Client(clientId,name,sex,age,idNumber,email,
                        tel,driveNumber,driveType,address,integral);
                list.add(client);
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
        return list;
    }

    @Override
    public int addClient(Client client) {
        String sql="insert into client(name,sex,age,id_number,email,tel,drive_number,drive_type,address,integral) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            int i = qr.update(JdbcUtils.getConnection(),sql,
                    client.getName(),client.getSex(),client.getAge(),client.getIdNumber(),
                    client.getEmail(),client.getTel(),client.getDriveNumber(),client.getDriveType(),client.getAddress(),
                    client.getIntegral());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteClient(int clientId) {
        String sql="delete from client where client_id = ?";
        try {
            int i = qr.update(JdbcUtils.getConnection(),sql,clientId);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateClient(Client client) {
        String sql="update client set name = ?,sex = ?,age = ?,id_number = ?,email = ?,tel = ?,drive_number = ?,drive_type = ?,address = ?,integral = ? where client_id = ?";
        try {
            int i = qr.update(JdbcUtils.getConnection(),sql,
                    client.getName(),client.getSex(),client.getAge(),client.getIdNumber(),
                    client.getEmail(),client.getTel(),client.getDriveNumber(),client.getDriveType(),client.getAddress(),
                    client.getIntegral(),client.getClientId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Client queryOne(int clientId) {
        String sql="select "+SQLCOL+" from client where client_id = ?";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,clientId);
            rs = ps.executeQuery();
            while (rs.next()){
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                int age = rs.getInt("age");
                String idNumber = rs.getString("id_number");
                String email = rs.getString("email");
                String tel = rs.getString("tel");
                String driveNumber = rs.getString("drive_number");
                String driveType = rs.getString("drive_type");
                String address = rs.getString("address");
                int integral = rs.getInt("integral");
                Client client =  new Client(clientId,name,sex,age,idNumber,email,
                        tel,driveNumber,driveType,address,integral);
                return client;
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
}
