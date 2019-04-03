package com.service;

import com.dao.CarsDao;
import com.dao.impl.CarsDaoImpl;
import com.entity.Cars;

import java.util.List;

/**
 * @autor : WL;
 * @date : 2019/3/28 0028
 */
public class CarsService {
    private CarsDao dao=new CarsDaoImpl();
    public List<Cars> findSuv(){
        return dao.findSuv();
    }

    public List<Cars> findC(){
        return dao.findC();
    }

    public List<Cars> findD(){
        return dao.findD();
    }

    public List<Cars> findBaoma(){
        return dao.findBaoma();
    }

    public List<Cars> findAodi(){
        return dao.findAodi();
    }

    public List<Cars> findBieke(){
        return dao.findBieke();
    }

    public List<Cars> findXiandai(){
        return dao.findXiandai();
    }

    public List<Cars> findDazhong(){
        return dao.findDazhong();
    }

    public List<Cars> findWoerwo(){
        return dao.findWoerwo();
    }

    public List<Cars> query(){
        return dao.query();
    }

}
