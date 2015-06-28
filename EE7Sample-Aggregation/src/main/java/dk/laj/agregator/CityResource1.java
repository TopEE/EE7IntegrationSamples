/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.laj.agregator;


import dk.laj.entities.CityInfo;
import dk.laj.entities.Hotel;
import dk.laj.entities.Weather;
import dk.laj.entities.WhatToSee;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Lars
 */
@Path("/city")
@Stateless
public class CityResource1 {

    @Resource
    ManagedExecutorService executor;

    String hotelUrl = "http://localhost:8182/Services/rest/hotel/";
    String weatherUrl = "http://localhost:8182/Services/rest/weather/";
    String whattoseeUrl = "http://localhost:8182/Services/rest/whattosee/";

    @Path("/{city}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public CityInfo getCityInfo(@PathParam("city") final String city) throws Exception {
        List<Callable<Object>> callers = new ArrayList<>();
        callers.add(() -> {
            return ClientBuilder.newClient().target(hotelUrl + city).request().get(Hotel.class);
        });
        callers.add(() -> {
            return ClientBuilder.newClient().target(weatherUrl + city).request().get(Weather.class);
        });
        callers.add(() -> {
            return ClientBuilder.newClient().target(whattoseeUrl + city).request().get(new GenericType<List<WhatToSee>>() {
            });
        });
        
        List<Future<Object>> futures = executor.invokeAll(callers, 1000, TimeUnit.MILLISECONDS);
        CityInfo cityInfo = new CityInfo();
        
        try {
            cityInfo.setHotel((Hotel) futures.get(0).get());
        } catch (CancellationException e) {
        }
        try {
            cityInfo.setWeather((Weather) futures.get(1).get());
        } catch (CancellationException e) {
        }
        try {
            cityInfo.setWhatToSee((List<WhatToSee>) futures.get(2).get());
        } catch (CancellationException e) {
        }

        return cityInfo;
    }
    
    

    
}
