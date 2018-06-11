package com.viswa.ctrail.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class OpenWeatherClient {

	public static String getCurrentWeather(String city) {
		city = (city==null)? "munchen":city;
		
		// Change it with your API key from https://openweathermap.org/api
		String AppID = "b6907d289e10d714a6e88b30761fae22";
		
		System.out.println("-- Getting results for the city: "+city);
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://api.openweathermap.org/data/2.5/forecast?q="+city+"&APPID="+AppID);
		String output = "";

		try {
			HttpResponse response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";

			while ((line = rd.readLine()) != null) {
				output += line;
			}
			rd.close();
		} catch (IOException e) {
			// do nothing.	
			System.out.println("Exception occured");
		}
		//System.out.println(output);
		JSONObject obj = new JSONObject(output);
		JSONArray arr = obj.getJSONArray("list");
		String result ="";
				for (int i = 0; i < arr.length(); i++)
				{
					if(arr.getJSONObject(i).has("main")){
						JSONObject tmpResult = arr.getJSONObject(i).getJSONObject("main");
						 result=tmpResult.get("temp").toString();
					}
				}		
		Double temperature = new Double(result);		
		temperature = temperature - 273.15F;
		DecimalFormat df = new DecimalFormat("#.##"); 
		temperature = Double.valueOf(df.format(temperature));
	    result = temperature.toString()+" C";
		return result;
	}

}
