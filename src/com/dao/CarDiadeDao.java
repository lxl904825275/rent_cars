package com.dao;


import com.entity.CarDiade;
import com.entity.PageBean;

import java.util.List;

/**
 * @author: Iman
 * date   : 2019/3/27
 */
public interface CarDiadeDao {
    public List<CarDiade> findAll();

    public int addCarDiade(CarDiade carDiade);

    public int findCarDiadeCountByGradeId(int gradeId);

    public CarDiade findCarDiadeById(int gradeId);

    public List<CarDiade> queryPage(PageBean pageBean);

    public int delete(int id);

    public int updateCarDiade(CarDiade carDiade);

    public List<CarDiade> getCarDiadeList(String sql);

    public int getConut();

    public  CarDiade queryOne(int id);


}
