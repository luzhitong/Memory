package com.ucas.memory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Jason on 6/1/2015.
 */
public class DataUtility {

    public static boolean register(JSONObject jsonObj) throws JSONException {
        if(jsonObj.getString("ret")=="1"){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean login(JSONObject jsonObj) throws JSONException {
        if(jsonObj.getString("ret")=="1"){
            System.out.println("======================login success====================");
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean add_memory(JSONObject jsonObj) throws JSONException{
        if(jsonObj.getString("ret")=="1"){
            return true;
        }
        else{
            return false;
        }
    }

    public static List<Map<String,String>> get_momery_list(JSONObject jsonObj) throws JSONException{
        JSONArray arry = jsonObj.getJSONObject("data").getJSONArray("memory");
        List<Map<String,String>> rsList = new ArrayList<Map<String,String>>();
        for (int i=0;i<arry.length();i++){
            JSONObject jsonObject = arry.getJSONObject(i);
            Map<String,String> map = new HashMap<String,String>();
            for (Iterator<?> iter = jsonObject.keys();iter.hasNext();){
                String key = (String)iter.next();
                String value = jsonObject.get(key).toString();
                map.put(key,value);
            }
            rsList.add(map);
        }

        return rsList;
    }
}
