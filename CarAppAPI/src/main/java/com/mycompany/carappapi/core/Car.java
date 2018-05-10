/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.carappapi.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author User
 */
public class Car {
    private String vin;
    private String carName;
    private List<CarInfo> carInfo;
    public Car(){
        carInfo = new ArrayList<>();
    }
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public List<CarInfo> getCarInfo() {
        return carInfo;
    }

    public void addCarInfo(CarInfo carInfo) {
        this.carInfo.add(carInfo);
    }

}
