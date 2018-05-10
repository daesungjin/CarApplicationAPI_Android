/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carappapi.dao;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 *
 * @author User
 */
public interface CarInfoDAO {
    @SqlUpdate("insert into car_info values(:id, :vin, :category, :info)")
    public void upgrade(@Bind("id") int id, @Bind("vin") String vin, @Bind("category") String category, @Bind("info") String info);
    @SqlUpdate("delete from car_info where vin = :vin and category = :category")
    public void delete(@Bind("vin") String vin, @Bind("category") String category);
}
