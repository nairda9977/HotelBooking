package com.solvd.hotels.Hotels;

public abstract class Hotels {
    String name;
    String checkIn;
    String checkOut;

     public Hotels(){

     }
     public Hotels(String name, String checkIn, String checkOut){
         this.name = name;
         this.checkIn = checkIn;
         this.checkOut = checkOut;
     }

    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
         return name;
    }
    public void setCheckIn(String checkIn) {
        this.checkIn = checkIn;
    }
    public String getCheckIn(){
        return checkIn;
    }
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
    public String getCheckOut(){
        return checkOut;
    }
/*    public void setHotelNumbers(String hotelNumbers) {
        this.hotelNumbers = hotelNumbers;
    }
    public String getHotelNumbers() {
        return hotelNumbers;
    }*/
}
