/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carappapi.core.mapper;

import com.mycompany.carappapi.core.Car;
import com.mycompany.carappapi.core.CarInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 *
 * @author User
 */
public class CarMapper implements ResultSetMapper<Car>{
Car car;
HashMap<String,Integer> tempMap = new HashMap<>();
    @Override
    public Car map(int i, ResultSet rs, StatementContext sc) throws SQLException {
        if(tempMap.get(rs.getString("vin"))==null){
            car = new Car();
            car.setCarName(rs.getString("name"));
            car.setVin(rs.getString("vin"));
            tempMap.put(rs.getString("vin"), 1);
            CarInfo carInfo = new CarInfo();
            carInfo.setCategory(rs.getString("category"));
            carInfo.setInfo(rs.getString("info"));
            car.addCarInfo(carInfo);
            return car;
        }
        CarInfo carInfo = new CarInfo();
        carInfo.setCategory(rs.getString("category"));
        carInfo.setInfo(rs.getString("info"));
        car.addCarInfo(carInfo);
        return null;
    }
    
}
