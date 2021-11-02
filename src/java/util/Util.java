/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Ericko
 */
public class Util {
    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list) {

        ArrayList<T> newList = new ArrayList<T>();

        for (T element : list) {
            if (!newList.contains(element)) {

                newList.add(element);
            }
        }
        return newList;
    }
    
    public static String convertDateToString(Date d)
    {
        String day = "";
        String year = "";
        String month = "";
        year += String.valueOf(d.getYear());
        if(d.getDate() < 10)
        {
            day += "0";
        }
        
        if(d.getMonth() < 10)
        {
            month += "0";
        }
        
        day += String.valueOf(d.getDate());
        
        month += String.valueOf(d.getMonth());
        
        String valiny = year + "-" + month + "-" + day;
        return valiny; 
    }
    
    public static String convertDateTimeToTimestamp(String dateTime)
    {
        String[] splits = dateTime.split("T");
        String valiny = splits[0]+" "+splits[1]+":00";
        System.out.println(valiny);
        return valiny;
    }
    
    public static Date convertStringToDate(String dateString)
    {
        String[] splits = dateString.split("-");
        Date d = new Date(Integer.parseInt(splits[0]),Integer.parseInt(splits[1]),Integer.parseInt(splits[2]));
        return d;
        
    }
}
