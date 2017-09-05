package com.example.proyecto1aplicad2017;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import android.content.Intent;

public class DoMethods {
	
	
	static JSONObject jsonUser;
	static JSONObject jsonProducts;
	
	//method to return jsonobject
	public static JSONObject getUser(String requestUser) {
		
	    try {	    		
	 
	    	//http url set request
	    	HttpGet request = new HttpGet(requestUser);
	        request.setHeader("Accept", "application/json");
	        request.setHeader("Content-type", "application/json");          
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpResponse response = httpClient.execute(request);    
	        
	        //stream
	        HttpEntity responseEntity = response.getEntity();   	       
	        char[] buffer = new char[(int)responseEntity.getContentLength()];
	        InputStream stream = responseEntity.getContent();
	        InputStreamReader reader = new InputStreamReader(stream);
	        reader.read(buffer);
	        stream.close();
	        

	        //set result into jsonobject
	        jsonUser = new JSONObject(new String(buffer));	 

	    } catch (Exception e) {	    	
	    	android.util.Log.i("Loi roi : ",e.toString());		
	    }	    
	    return jsonUser;
	}
	
	//method to return jsonobject
	public static JSONObject getAllProducts(String requestProduct) {
		
		android.util.Log.i("WCFIP",requestProduct);
	    try {	    		
	        //http request url
	    	HttpGet request = new HttpGet(requestProduct);
	        request.setHeader("Accept", "application/json");
	        request.setHeader("Content-type", "application/json");          
	        DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpResponse response = httpClient.execute(request);    
	        
	        // stream
	        HttpEntity responseEntity = response.getEntity();   	       
	        char[] buffer = new char[(int)responseEntity.getContentLength()];
	        InputStream stream = responseEntity.getContent();
	        InputStreamReader reader = new InputStreamReader(stream);
	        reader.read(buffer);
	        
	        stream.close();
	        
	               
	        
	        //set jsonobject
	        jsonProducts = new JSONObject(new String(buffer));	  
	        
	    } catch (Exception e) {	    	
	    	android.util.Log.i("Loi roi : ",e.toString());		
	    }	    
	    return jsonProducts;
	}
	

}
