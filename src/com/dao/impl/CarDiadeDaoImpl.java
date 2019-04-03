package com.dao.impl;


import com.dao.CarDiadeDao;
import com.entity.CarDiade;
import com.entity.PageBean;
import com.framework.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: Iman
 * date   : 2019/3/27
 */
public class CarDiadeDaoImpl implements CarDiadeDao {
    private QueryRunner queryRunner = new QueryRunner();
    @Override
    public List<CarDiade> findAll() {
        List<CarDiade> list=new ArrayList<>();
        String sql="select * from car_grade ";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int gradeId = rs.getInt("grade_id");
                String grade = rs.getString("grade");
                int vv_tax = rs.getInt("vv_tax");

                int isn_p = rs.getInt("isn_p");
                int main_c = rs.getInt("main_c");
                int fuel_c = rs.getInt("fuel_c");
                int invoice_t = rs.getInt("invoice_t");
                int v_manager = rs.getInt("v_manager");

                CarDiade carDiade = new CarDiade(gradeId,grade,vv_tax,isn_p,main_c,fuel_c,invoice_t,v_manager);
                list.add(carDiade);


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
    public int addCarDiade(CarDiade carDiade) {
        String sql ="INSERT INTO car_grade ( grade, vv_tax,  isn_p, main_c, fuel_c,invoice_t,v_manager) " +
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);

            Object[] obj = {carDiade.getGrade(),carDiade.getVvTax(),carDiade.getIsnP(),carDiade.getMainC(),carDiade.getFuelC(),carDiade.getInvoiceT(),carDiade.getvManager()};
            setParten(ps,obj);
            int i = ps.executeUpdate();
            return  i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close();
        }
        return 0;
    }

    private void setParten(PreparedStatement ps, Object[] obj) {
        for (int i = 0; i < obj.length ; i++) {
            try {
                ps.setObject(1+i,obj[i]);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int findCarDiadeCountByGradeId(int gradeId) {
        String sql="select count(*)  count from car_grade where gradeId=?";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,gradeId);
            rs = ps.executeQuery();
            while (rs.next()){
                int count = rs.getInt("count");
                return  count;
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
    public CarDiade findCarDiadeById(int gradeId) {
        String sql="select * from car_grade where grade_id=?";
        CarDiade carDiade =  new CarDiade();

        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,gradeId);
            rs = ps.executeQuery();
            while (rs.next()){

                carDiade.setGrade(rs.getString("grade_id"));
                carDiade.setGrade(rs.getString("grade"));
                carDiade.setVvTax(rs.getInt("vv_tax"));

                carDiade.setIsnP(rs.getInt("isn_p"));
                carDiade.setMainC(rs.getInt("main_c"));
                carDiade.setFuelC(rs.getInt("fuel_c"));
                carDiade.setInvoiceT(rs.getInt("invoice_t"));
                carDiade.setvManager(rs.getInt("v_manager"));


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
        return carDiade;
    }

    @Override
    public List<CarDiade> queryPage(PageBean pageBean) {
        String sql="select * from  car_grade  order by grade_id  limit ?,?";
        List<CarDiade> list =  new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBean.getIndex());
            ps.setInt(2,pageBean.getPageCount());
            rs = ps.executeQuery();
            while (rs.next()){
                CarDiade carDiade =  new CarDiade();
                carDiade.setGradeId(rs.getInt("grade_id"));

                carDiade.setGrade(rs.getString("grade"));
                carDiade.setVvTax(rs.getInt("vv_tax"));

                carDiade.setIsnP(rs.getInt("isn_p"));
                carDiade.setMainC(rs.getInt("main_c"));
                carDiade.setFuelC(rs.getInt("fuel_c"));
                carDiade.setInvoiceT(rs.getInt("invoice_t"));
                carDiade.setvManager(rs.getInt("v_manager"));
                carDiade.setCosts(rs.getInt("vv_tax"),rs.getInt("isn_p"),rs.getInt("main_c"),rs.getInt("fuel_c"),
                        rs.getInt("invoice_t"),rs.getInt("v_manager"));

                list.add(carDiade);
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
    public int delete(int id) {
        String sql ="delete from car_grade where  grade_id = ?";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            int i = ps.executeUpdate();
            return  i;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close();
        }
        return  0;
    }

    @Override
    public int updateCarDiade(CarDiade carDiade) {
        String sql ="update car_grade  set vv_tax=?," +
                "isn_p=?,main_c=?,fuel_c=?,invoice_t=? ,v_manager=? where grade_id=?";
        try {
            int i = queryRunner.update(JdbcUtils.getConnection(), sql,carDiade.getVvTax(),carDiade.getIsnP(),
                    carDiade.getMainC(),carDiade.getFuelC(),carDiade.getInvoiceT(),carDiade.getvManager(),carDiade.getGradeId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public List<CarDiade> getCarDiadeList(String sql) {
        List<CarDiade> list = new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){

                    CarDiade carDiade =  new CarDiade();
                    carDiade.setGradeId(rs.getInt("grade_id"));

                    carDiade.setGrade(rs.getString("grade"));
                    carDiade.setVvTax(rs.getInt("vv_tax"));

                    carDiade.setIsnP(rs.getInt("isn_p"));
                    carDiade.setMainC(rs.getInt("main_c"));
                    carDiade.setFuelC(rs.getInt("fuel_c"));
                    carDiade.setInvoiceT(rs.getInt("invoice_t"));
                    carDiade.setvManager(rs.getInt("v_manager"));
                    list.add(carDiade);

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

        String sql="select count(*) count from car_grade ";
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
    public CarDiade queryOne(int id) {
        String sql="select * from car_grade  where grade_id = ?";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                int gradeId = rs.getInt("grade_id");
                String grade = rs.getString("grade");
                int vv_tax = rs.getInt("vv_tax");

                int isn_p = rs.getInt("isn_p");
                int main_c = rs.getInt("main_c");
                int fuel_c = rs.getInt("fuel_c");
                int invoice_t = rs.getInt("invoice_t");
                int v_manager = rs.getInt("v_manager");
                CarDiade carDiade = new CarDiade(gradeId,grade,vv_tax,isn_p,main_c,fuel_c,invoice_t,v_manager);
                return carDiade;
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
