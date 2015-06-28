package dk.laj.agregator;

import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

@Path("/city")
@Stateless
public class CityNoTimeOutResource {
    @Resource ManagedExecutorService executor;
    static final String URL = "http://localhost:8182/Services/rest/";

    @Path("/{city}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getCityInfo(@PathParam("city") final String city) throws Exception {
        Future<JsonObject> f1 = executor.submit(() -> {
            return ClientBuilder.newClient().target(URL+"hotel/"+city).request().get(JsonObject.class);
        });
        Future<JsonObject> f2 = executor.submit(() -> {
            return ClientBuilder.newClient().target(URL+"weather/"+city).request().get(JsonObject.class);
        });
        Future<JsonArray> f3 = executor.submit(() -> {
            return ClientBuilder.newClient().target(URL+"whattosee/"+city).request().get(JsonArray.class);
        });
       return  Json.createObjectBuilder().add("hotel", f1.get()).add("weather", f2.get()).add("whattosee", f3.get()).build();
    }
}