package com.solvd.hotels.Operation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SequenceWriter;
import com.solvd.hotels.Hotels.MirabelAntaliaResort;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.DateTimeException;
import java.time.LocalDate;

import java.util.Scanner;

public class Operation {


    public void booking(){
        System.out.println("Print name number");
        Scanner nameNumSc = new Scanner(System.in);
        String nameNum = nameNumSc.nextLine();
        System.out.println("Print checkIn date please");
        System.out.println("year-month-date");
        Scanner checkInSc = new Scanner(System.in);
        String checkInStr = checkInSc.nextLine();
        LocalDate checkIn=null;
        try {
            LocalDate localDate = LocalDate.now();
            checkIn = LocalDate.parse(checkInStr);
            if(checkIn.compareTo(localDate)<=0){
                System.err.println("Booking date cant be in past");
                System.exit(0);
            }
        }catch (DateTimeException e ){
            System.out.println("Enter right data, start again");
            System.exit(0);
        }
        System.out.println("Print checkOut date please");
        System.out.println("year-month-date");
        Scanner checkOutSc= new Scanner(System.in);
        String checkOutStr = checkOutSc.nextLine();
        LocalDate checkOut = null;
        try {
            checkOut = LocalDate.parse(checkOutStr);
        }catch (DateTimeException e){
            System.out.println("Enter right data, start again");
            System.exit(0);
        }

        if(checkOut.compareTo(checkIn)>=1){
            System.out.println("Booked");
        }else System.out.println("CheckIn cant be latest than checkOut");

        try {
            File file = new File("mira.json");
            FileWriter fileWriter = new FileWriter(file, true);

            ObjectMapper mapper = new ObjectMapper();

            SequenceWriter seqWriter = mapper.writer().writeValues(fileWriter);

            seqWriter.write(new MirabelAntaliaResort(nameNum, checkInStr, checkOutStr));

            seqWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
