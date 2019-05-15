package com.leone.boot.common.design.structure.bridge;

public class Client {

    public static void main(String[] args) {

        DriverManager driverManager = new MyDriverManager();
        Driver driver1 = new CPUDriver();
        driverManager.setDriver(driver1);
        driverManager.connect();

        Driver driver2 = new DiskDriver();
        driverManager.setDriver(driver2);
        driverManager.connect();

    }
}
