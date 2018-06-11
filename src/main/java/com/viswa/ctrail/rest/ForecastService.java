package com.viswa.ctrail.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/fc")
public class ForecastService {

	@GET
	@Path("/{city}")
	public String getFC(@PathParam("city") String city){
		String result = OpenWeatherClient.getCurrentWeather(city);
		return "Temparature in "+city+ " is :"+result;
	}
}
