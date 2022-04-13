package com.trongit.utils;
import java.util.Map;

public class MapUtil {
    public static Object getValue(Map<String,Object> params,String key){
        return params.containsKey(key)?params.get(key):null;
    }
}
