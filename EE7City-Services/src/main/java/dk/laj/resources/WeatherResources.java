/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.laj.resources;

import dk.laj.entities.Weather;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Lars
 */
@Path("/weather")
public class WeatherResources {
    @GET
    @Path("/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public Weather getWeather(@PathParam("city") String city ) throws InterruptedException{
        Thread.sleep((int)(Math.random()*1500));
        if("copenhagen".equalsIgnoreCase(city)){
            return new Weather(10,"cloudy");
        }
        if("rome".equalsIgnoreCase(city)){
            return new Weather(25,"sunny");
        }
        throw new NotFoundException("No such city");
    }
}
