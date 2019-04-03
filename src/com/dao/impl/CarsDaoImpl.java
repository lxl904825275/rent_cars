package com.dao.impl;

import com.dao.CarsDao;
import com.entity.Cars;
import com.entity.PageBean;
import com.framework.JdbcUtil;
import com.framework.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @autor : WL;
 * @date : 2019/3/28 0028
 */
public class CarsDaoImpl implements CarsDao {

    private QueryRunner queryRunner = new QueryRunner();
    @Override
    public List<Cars> findAll() {
        String sql="select * from cars order by car_id ";
        List<Cars> list = new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                int id = rs.getInt("car_id");

                String carName = rs.getString("car_name");

                int gradeId = rs.getInt("grade_id");

                int isImage = rs.getInt("is_image");
                String imageUrl = rs.getString("image_url");
                int dailyRent = rs.getInt("daily_rent");
                int monthRent = rs.getInt("month_rent");
                int num = rs.getInt("num");
                String context = rs.getString("context");
                Cars cars =  new Cars(id,carName,gradeId,isImage,imageUrl,dailyRent,monthRent,num,context);
                list.add(cars);
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
    public int addCars(Cars cars) {
        String sql ="INSERT INTO cars ( car_name, grade_id, image_url, daily_rent,month_rent,num,context) " +
                "VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);

            Object[] obj = {cars.getCarName(),cars.getGradeId(),cars.getImageUrl(),cars.getDailyRent(),cars.getMonthRent(),cars.getNum(),cars.getContext()};
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

    public void setParten(PreparedStatement ps,Object... obj) throws SQLException {
        for (int i = 0; i < obj.length ; i++) {
            ps.setObject(1+i,obj[i]);
        }

    }



    @Override
    public List<Cars> findCarsByCarName(String carName) {
        String sql="select * from cars where car_name="+carName;
        return getCarsList(sql);
    }

    @Override
    public int findCarsCountByGradeId(int gradeId) {
        String sql="select count(*)  count from cars where gradeId=?";
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
    public int findCarsCountById(int id) {
        String sql="select count(*)  count from cars where id=?";
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
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
    public Cars findCarsById(int id) {
        String sql="select c.*,g.grade_id from cars c , car_grade g  where c.grade_id = g.grade_id and car_id=?";
        Cars cars =  new Cars();

        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                cars.setCar_Id(rs.getInt("car_id"));

                cars.setCar_Name(rs.getString("car_name"));
                cars.setGrade_Id(rs.getInt("grade_id"));

                cars.setIs_Image(rs.getInt("is_image"));
                cars.setImage_Url(rs.getString("image_url"));
                cars.setDaily_Rent(rs.getInt("daily_rent"));
                cars.setMonth_Rent(rs.getInt("month_rent"));
                cars.setNum(rs.getInt("num"));
                cars.setContext(rs.getString("context"));
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
        return cars;

    }

    @Override
    public List<Cars> queryPage(PageBean pageBean) {

        String sql="select * from cars c , car_grade g where c.grade_id =g.grade_id order by car_id limit ?,?";
        List<Cars> list =  new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs = null;
        try {
            Connection connection = JdbcUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setInt(1,pageBean.getIndex());
            ps.setInt(2,pageBean.getPageCount());
            rs = ps.executeQuery();
            while (rs.next()){
                Cars cars =  new Cars();
                cars.setCar_Id(rs.getInt("car_id"));

                cars.setCar_Name(rs.getString("car_name"));
                cars.setGrade_Id(rs.getInt("grade_id"));

                cars.setIs_Image(rs.getInt("is_image"));
                cars.setImage_Url(rs.getString("image_url"));
                cars.setDaily_Rent(rs.getInt("daily_rent"));
                cars.setMonth_Rent(rs.getInt("month_rent"));
                cars.setNum(rs.getInt("num"));
                cars.setContext(rs.getString("context"));
                list.add(cars);
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
        String sql ="delete from cars where  car_id = ?";
        try {
            int i = queryRunner.update(JdbcUtil.getConnection(), sql, id);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  0;
    }

    @Override
    public int updateCars(Cars cars) {
        String sql ="update cars  set car_name=? ,grade_id=?," +
                "daily_rent=?,month_rent=?,num=?,context=? where car_id=?";
        try {
            int i = queryRunner.update(JdbcUtils.getConnection(), sql, cars.getCarName(), cars.getGradeId(), cars.getDailyRent(), cars.getMonthRent(), cars.getNum(), cars.getContext(), cars.getCarId());
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<Cars> getCarsList(String sql) {
        List<Cars> list = new ArrayList<>();
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            Connection con= JdbcUtil.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cars car=new Cars();
                int id = rs.getInt("car_id");
                String carName = rs.getString("car_name");
                int gradeId = rs.getInt("grade_id");
                int isImage = rs.getInt("is_image");
                String imageUrl = rs.getString("image_url");
                int dailyRent = rs.getInt("daily_rent");
                int monthRent = rs.getInt("month_rent");
                int num = rs.getInt("num");
                String context = rs.getString("context");
                car.setCar_Id(id);
                car.setCar_Name(carName);
                car.setGrade_Id(gradeId);
                car.setIs_Image(isImage);
                car.setImage_Url(imageUrl);
                car.setDaily_Rent(dailyRent);
                car.setMonth_Rent(monthRent);
                car.setNum(num);
                car.setContext(context);
                System.out.println(car);
                list.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JdbcUtil.close();
        }
        return list;
    }

    @Override
    public List<Cars> query() {
        String sql = "select * from cars ";
        return getCarsList(sql);
    }

    @Override
    public List<Cars> findSuv() {
        String sql = "select * from cars where grade_id = 3 order by daily_rent desc";
        return getCarsList(sql);
    }

    @Override
    public List<Cars> findC() {
        String sql = "select * from cars where grade_id = 1 order by daily_rent desc";
        return getCarsList(sql);
    }

    @Override
    public List<Cars> findD() {
        String sql = "select * from cars where grade_id = 2 order by daily_rent desc";
        return getCarsList(sql);
    }

    @Override
    public List<Cars> findBaoma() {
        String sql = "select * from cars where car_name like '宝马%'";
        return getCarsList(sql);
    }

    @Override
    public List<Cars> findAodi() {
        String sql = "select * from cars where car_name like '奥迪%'";
        return getCarsList(sql);
    }

    @Override
    public List<Cars> findBieke() {
        String sql = "select * from cars where car_name like '别克%'";
        return getCarsList(sql);
    }

    @Override
    public List<Cars> findXiandai() {
        String sql = "select * from cars where car_name like '现代%'";
        return getCarsList(sql);
    }

    @Override
    public List<Cars> findDazhong() {
        String sql = "select * from cars where car_name like '大众%'";
        return getCarsList(sql);
    }

    @Override
    public List<Cars> findWoerwo() {
        String sql = "select * from cars where car_name like '沃尔沃%'";
        return getCarsList(sql);
    }
}
