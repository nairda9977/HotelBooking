package com.solvd.hotels.Hotels;

public class Room1 {
    private String checkIn;
    private String checkOut;

    public Room1(){

    }



    public Room1(String checkIn, String checkOut){
         this.checkIn=checkIn;
         this.checkOut=checkOut;
    }

    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getCheckIn() {
        return checkIn;
    }

    public String getCheckOut() {
        return checkOut;
    }

    @Override
    public String toString(){
        return "checkIn "+checkIn+"  checkOut "+checkOut;
    }
}
