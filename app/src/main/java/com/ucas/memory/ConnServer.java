package com.ucas.memory;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Jason on 6/1/2015.
 */
public class ConnServer {
    static final String ROOT = "http://124.16.79.238/memory/mobile";
    static final String API_REGISTER = ROOT+"/register";
    static final String API_LOGIN = ROOT+"/login";
    static final String API_MEMORY_ADD = ROOT+"/memory_add";
    static final String API_MEMORY_LIST = ROOT+"/memories";

    static HttpContext localContext=null;
    static DefaultHttpClient httpClient = null;
    static String session_id=null;

    public static JSONObject login(String email,String pwd) throws JSONException, IOException {
        JSONObject ClientKey = new JSONObject();
        ClientKey.put("email",email);
        ClientKey.put("pwd",pwd);

        BasicCookieStore localCookies = null;
        localCookies = new BasicCookieStore();
        localContext = new BasicHttpContext();
        localContext.setAttribute(ClientContext.COOKIE_STORE,localCookies);

        HttpPost request = new HttpPost(API_LOGIN);

        StringEntity se = new StringEntity(ClientKey.toString());
        request.setEntity(se);

        httpClient = new DefaultHttpClient();

        HttpResponse httpResponse = httpClient.execute(request,localContext);

        String retSrc = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        JSONObject jsonObj = new JSONObject(retSrc);

        session_id =  jsonObj.getJSONObject("data").getString("session_id");
        return jsonObj;
    }

    public static JSONObject register(String email,String nickname,String pwd) throws JSONException, IOException {
        JSONObject ClientKey = new JSONObject();
        ClientKey.put("email",email);
        ClientKey.put("nickname",nickname);
        ClientKey.put("pwd",pwd);

        BasicCookieStore localCookies = null;
        localCookies = new BasicCookieStore();
        localContext = new BasicHttpContext();
        localContext.setAttribute(ClientContext.COOKIE_STORE,localCookies);

        HttpPost request = new HttpPost(API_LOGIN);

        StringEntity se = new StringEntity(ClientKey.toString());
        request.setEntity(se);

        httpClient = new DefaultHttpClient();

        HttpResponse httpResponse = httpClient.execute(request,localContext);

        String retSrc = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

        JSONObject jsonObj = new JSONObject(retSrc);

        session_id =  jsonObj.getJSONObject("data").getString("session_id");
        return jsonObj;
    }

    public static JSONObject add_memory(String title,String location,String datetime,String content,String people,String picture) throws JSONException, IOException {
        JSONObject ClientKey = new JSONObject();

        ClientKey.put("session_id",session_id);
        ClientKey.put("title",title);
        ClientKey.put("location",location);
        ClientKey.put("datetime",datetime);
        ClientKey.put("content",content);
        ClientKey.put("people",people);
        ClientKey.put("picture",picture);

        HttpPost request = new HttpPost(API_MEMORY_ADD);

        StringEntity se = new StringEntity(ClientKey.toString());
        request.setEntity(se);

        HttpResponse httpResponse = httpClient.execute(request,localContext);

        String retSrc = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
        JSONObject jsonObj = new JSONObject(retSrc);
        return jsonObj;
    }

    public static JSONObject get_momery_list(String start_time,String end_time,int start_num,int num) throws JSONException, IOException {
        JSONObject ClientKey = new JSONObject();
        ClientKey.put("start_time",start_time);
        ClientKey.put("end_time",end_time);
        ClientKey.put("start_num",start_num);
        ClientKey.put("num",num);

        HttpPost request = new HttpPost(API_MEMORY_LIST);

        StringEntity se = new StringEntity(ClientKey.toString());
        request.setEntity(se);
        // request2.addHeader("Cookie", session_id);

        HttpResponse httpResponse = httpClient.execute(request,localContext);

        String retSrc = EntityUtils.toString(httpResponse.getEntity(),"UTF-8");
        System.out.println(retSrc);
        JSONObject jsonObj = new JSONObject(retSrc);
        return jsonObj;
    }
}