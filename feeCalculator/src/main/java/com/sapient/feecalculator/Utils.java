package com.sapient.feecalculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static Date parseDate(String date) {
        try{
          SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
          Date convertedCurrentDate = sdf.parse(date);
          System.out.println(convertedCurrentDate);
          return convertedCurrentDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
