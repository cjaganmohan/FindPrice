package com.example.findprice;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;


public class DisplayProductInfo extends Activity {
	
	
	String text;
	TextView textview;
	private static ArrayList<DisplayProduct> list = new ArrayList<DisplayProduct>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_results);
		
		Intent myIntent = getIntent();
		String upccode = myIntent.getStringExtra("barcode");
		
		String URL1 = "http://www.searchupc.com/handlers/upcsearch.ashx?request_type=3&access_token=5D966CEE-C823-49A0-85FD-7FD2381F7343&upc=";
		String URL2 = upccode;
		String url = URL1+URL2;
		connect(url);
		}   
	
	public void connect(String url)
	{
	    
	    AsyncHttpClient client = new AsyncHttpClient();
	    client.get(url, new AsyncHttpResponseHandler() {
	        @Override
	        public void onSuccess(String response) {
//	            
	        	printdata(response);
	        	
	        }
	    });	      
	}

	public void printdata(String response) 
	{
		
		
		DisplayProduct dp = getItem(response);
		if(dp==null)
		{
			return;
		}
		System.out.println(dp.getProductname());
		System.out.println(dp.getPrice());
		System.out.println(dp.getProducturl());
		textview = (TextView) findViewById(R.id.testurl);
		textview.setText(dp.productname);
		
	}
	
	public static DisplayProduct getItem(String jsontext){
		Gson gson = new Gson();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		list.clear();

		try {
			node = mapper.readTree(jsontext);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Iterator<Entry<String, JsonNode>> fieldNames = node.fields();
		while (fieldNames.hasNext()) {
			Entry<String, JsonNode> fieldName = fieldNames.next();
			String genjson = fieldName.getValue().toString();
			//System.out.println(genjson);
			//System.out.println(fieldName);// prints title, message, errors,
			
			DisplayProduct dp = gson.fromJson(genjson, DisplayProduct.class);
			//System.out.println(dp.toString());
			
			if(dp.getCurrency() != null)
			{
				if(dp.getCurrency().equals("USD"))
					list.add(dp);
			}else{
				list.add(dp);
			}
		}
		if(list.size()==0)
		{
			return null;
		}
		
		DisplayProduct newproduct = list.get(0);
		
		for(DisplayProduct dp: list)
		{
			if(dp.getPrice() < newproduct.getPrice())
				newproduct = dp;
		}
		
		return newproduct;
	}
			
		
	}

	
	
