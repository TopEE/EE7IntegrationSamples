/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.laj.resources;

import dk.laj.entities.Hotel;
import javax.ws.rs.Consumes;
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
@Path("/hotel")
public class HotelResource {
    @GET
    @Path("/{city}")
    @Produces({MediaType.APPLICATION_JSON})
    public Hotel getHotels(@PathParam("city") String city ) throws Exception{
        Thread.sleep((int)(Math.random()*1500));
        if("copenhagen".equalsIgnoreCase(city)){
            return new Hotel("Raadhuspladsen 4","Tivoli Hotel");
        }
        if("rome".equalsIgnoreCase(city)){
            return new Hotel("Traste Vere","Hilton Coloseum");
        }
        throw new NotFoundException("No such city");
    }
}
