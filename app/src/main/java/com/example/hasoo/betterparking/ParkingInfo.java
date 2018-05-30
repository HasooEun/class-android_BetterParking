package com.example.hasoo.betterparking;

public class ParkingInfo {

    private String carType, carNum;
    private long parkingTime;
    private boolean parked;

    ParkingInfo(){
        this.carType = "";
        this.carNum = "";
        this.parkingTime = 0l;
        this.parked = false;
    };

    public void setParkingTime(String carType, String carNum, long parkingTime){
        this.carType = carType;
        this.carNum = carNum;
        this.parkingTime = parkingTime;
        this.parked = true;
    }

    public String getCarType(){
        return carType;
    }

    public String getCarNum(){
        return carNum;
    }

    public long getParkingTime(){
        return parkingTime;
    }

    public boolean isParked(){
        return parked;
    }
}
