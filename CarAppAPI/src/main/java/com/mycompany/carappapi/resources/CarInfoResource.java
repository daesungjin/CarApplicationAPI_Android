/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carappapi.resources;

import com.mycompany.carappapi.dao.CarInfoDAO;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 *
 * @author User
 */
@Path("/carinfo")
public class CarInfoResource {
    private CarInfoDAO carInfoDAO;

    public CarInfoResource(CarInfoDAO carInfoDAO) {
        this.carInfoDAO = carInfoDAO;
    }
    
    @POST
    public void upgrade(@FormParam("id") int id, @FormParam("vin") String vin, @FormParam("category")String category, @FormParam("info") String info){
        carInfoDAO.upgrade(id, vin, category, info);
    }
    @DELETE
    public void delete(@FormParam("vin") String vin, @FormParam("category") String category){
        carInfoDAO.delete(vin, category);
    }
}
