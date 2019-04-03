package com.dao;

import com.entity.Cars;
import com.entity.PageBean;

import java.util.List;

/**
 * @autor : WL;
 * @date : 2019/3/26 0026
 */
public interface CarsDao {
    public List<Cars> query();

    public List<Cars> findSuv();

    public List<Cars> findC();

    public List<Cars> findD();

    public List<Cars> findBaoma();

    public List<Cars> findAodi();

    public List<Cars> findBieke();

    public List<Cars> findXiandai();

    public List<Cars> findDazhong();

    public List<Cars> findWoerwo();

    public List<Cars> findAll();

    public int addCars(Cars cars);



    public List<Cars> findCarsByCarName(String carName);

    public int findCarsCountByGradeId(int gradeId);

    public int findCarsCountById(int id);

    public Cars findCarsById(int id);

    public List<Cars> queryPage(PageBean pageBean);

    public int delete(int id);

    public int updateCars(Cars cars);




}
