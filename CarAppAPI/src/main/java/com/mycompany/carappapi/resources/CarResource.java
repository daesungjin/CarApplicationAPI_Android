/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carappapi.resources;

import com.mycompany.carappapi.core.Car;
import com.mycompany.carappapi.dao.CarDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;

import javax.ws.rs.GET;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author User
 */
@Path("/car")
//@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {
    private CarDAO carDAO;
    public CarResource(CarDAO carDAO){
        this.carDAO = carDAO;
    }
    @GET
    public List<Car> show(){
        return this.findAll();
    }
    @GET
    @Path("/{vin}")
    public List<Car> display(@PathParam("vin")String vin){
        return this.findCar(vin);
    }
    public List<Car> findCar(String vin){
        List<Car> temp = carDAO.findCar(vin);
        HashMap<String,Integer> tempMap = new HashMap<>();
        List<Car> list = new ArrayList<>();
        for(Car car : temp){
            if(car!=null){               
                list.add(car);
            } 
        }
        return list;
    }
    public List<Car> findAll(){
        List<Car> temp = carDAO.findAll();
        HashMap<String,Integer> tempMap = new HashMap<>();
        List<Car> list = new ArrayList<>();
        for(Car car : temp){
            if(car!=null){               
                list.add(car);
            } 
        }
        return list;
    }
    @POST
    public List<Car> create(@FormParam("carName") String carName, @FormParam("vin") String vin){
        carDAO.registerCar(carName, vin);
        return carDAO.findAll();
    }
   @DELETE
    public void delete(@FormParam("vin") String vin){
        carDAO.deleteCar(vin);
    }
}
