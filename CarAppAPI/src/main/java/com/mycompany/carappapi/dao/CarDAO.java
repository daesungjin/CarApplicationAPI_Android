/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carappapi.dao;

import com.mycompany.carappapi.core.Car;
import com.mycompany.carappapi.core.mapper.CarMapper;
import java.util.List;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 *
 * @author User
 */
public interface CarDAO {
    @Mapper(CarMapper.class)
    @SqlQuery("select car.name, car.vin, category, info  from car_info as ci left join car on ci.vin = car.vin")
    List<Car> findAll();
    @Mapper(CarMapper.class)
    @SqlQuery("select car.name, car.vin, category,info  from car_info as ci join car on ci.vin = car.vin where car.vin = :vin")
    List<Car> findCar(@Bind("vin") String vin);
    @Mapper(CarMapper.class)
    @SqlUpdate("insert into car values(:vin, :carName)")
    void registerCar(@Bind("carName") String carName, @Bind("vin") String vin);
    @Mapper(CarMapper.class)
    @SqlUpdate("delete from car where vin = :vin")
    void deleteCar(@Bind("vin") String vin);
}
