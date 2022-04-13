package com.trongit.utils;

public class ParseIntUtil {
    public static Integer getValue(Object value){
        try{
            return Integer.parseInt((String) value);
        }catch (Exception e){
            return null;
        }
    }
}
