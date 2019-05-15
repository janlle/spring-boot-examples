package com.leone.boot.common.design.structure.bridge;

public abstract class DriverManager {
 
    private Driver driver;
 
 
    public void connect() {
        driver.connect();
    }
 
    public Driver getDriver() {
        return driver;
    }
 
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
 
}