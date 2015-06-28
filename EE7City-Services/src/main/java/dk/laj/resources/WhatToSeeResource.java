/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.laj.resources;

import dk.laj.entities.WhatToSee;
import java.util.ArrayList;
import java.util.List;
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
@Path("/whattosee")
public class WhatToSeeResource {

   
    @GET
    @Path("/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WhatToSee> getWhatToSee(@PathParam("city") String city ) throws InterruptedException{
        Thread.sleep((int)(Math.random()*1500));
        if("copenhagen".equalsIgnoreCase(city)){
            List<WhatToSee> list = new ArrayList<>();
            list.add(new WhatToSee("Litle Mermaid"));
            return list;
        }
        if("rome".equalsIgnoreCase(city)){
             List<WhatToSee> list = new ArrayList<>();
            list.add(new WhatToSee("Colloseum"));
             return list;
        }
        throw new NotFoundException("No such city");
    }
}
