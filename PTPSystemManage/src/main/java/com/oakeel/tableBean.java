/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oakeel;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.DragDropEvent;

/**
 *
 * @author root
 */
@ManagedBean
@RequestScoped
public class tableBean {

    /**
     * Creates a new instance of tableBean
     */
    CarService cs;
    private List<Car> availableCars;
    private List<Car> droppedCars;

    public tableBean() {
    }

    @PostConstruct
    public void init() {
        cs = new CarService();
        setAvailableCars(cs.createCars(10));
        setDroppedCars(new ArrayList<Car>());
    }

    public void onCarDrop(DragDropEvent event) {
        Car car = ((Car) event.getData());
        droppedCars.add(car);
        availableCars.remove(car);
    }

    /**
     * @return the availableCars
     */
    public List<Car> getAvailableCars() {
        return availableCars;
    }

    /**
     * @param availableCars the availableCars to set
     */
    public void setAvailableCars(List<Car> availableCars) {
        this.availableCars = availableCars;
    }

    /**
     * @return the droppedCars
     */
    public List<Car> getDroppedCars() {
        return droppedCars;
    }

    /**
     * @param droppedCars the droppedCars to set
     */
    public void setDroppedCars(List<Car> droppedCars) {
        this.droppedCars = droppedCars;
    }
}
