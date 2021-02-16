package com.solvd.hotels.Hotels;


import java.util.List;

public class Root extends AntaliaHotels{
    private String name;
    private List <Room1> room1;




    public void setName(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public List<Room1> getRoom1() {
        return room1;
    }

    public void setRoom1(List<Room1> room1) {
        this.room1 = room1;
    }

    @Override
    public String toString() {
        return "Root{" +
                "name='" + name + '\'' +
                ", room1=" + room1 +
                '}';
    }
}
