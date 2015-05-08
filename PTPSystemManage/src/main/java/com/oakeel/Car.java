/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oakeel;

/**
 *
 * @author root
 */
public class Car {
    private String uuid;
    private String brand;
    private int year;
    private String color;
    private int price;
    private Boolean soldState;

    public Car(String uuid, String brand, int year, String color, int price, Boolean soldState) {
        this.uuid = uuid;
        this.brand = brand;
        this.year = year;
        this.color = color;
        this.price = price;
        this.soldState = soldState;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    /**
     * @return the brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand the brand to set
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * @return the soldState
     */
    public Boolean getSoldState() {
        return soldState;
    }

    /**
     * @param soldState the soldState to set
     */
    public void setSoldState(Boolean soldState) {
        this.soldState = soldState;
    }

  
 
}
