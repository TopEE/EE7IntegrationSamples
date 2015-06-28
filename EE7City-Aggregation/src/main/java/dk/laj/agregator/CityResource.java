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
public class CityResource {

    @Resource
    ManagedExecutorService executor;

    @Path("/{city}")
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public JsonObject getCityInfo(@PathParam("city") final String city) throws Exception {
        Future<JsonObject> f1 = executor.submit(() -> { return getCurrentWeather(city);  });
        Future<JsonObject> f2 = executor.submit(() -> { return getCountryInfo(city);});
        JsonObject info = f2.get();
        String exch = getCurrencyExchangeRate(info.getJsonString("currency").getString());
        return Json.createObjectBuilder().add("weatherdate", f1.get()).add("info", info).add("vaekslekurs", exch).build();
    }

    private JsonObject getCountryInfo(String city) {
        JsonObject w = ClientBuilder.newClient().target("http://restcountries.eu/rest/v1/capital/" + city.toLowerCase()).request().get(JsonArray.class).getJsonObject(0);
        JsonObjectBuilder jb = Json.createObjectBuilder();
        jb.add("currency", w.getJsonArray("currencies").getJsonString(0));
        jb.add("languages", w.getJsonArray("languages"));
        return jb.build();
    }

    private JsonObject getCurrentWeather(String city) {
        JsonObject w = ClientBuilder.newClient().target("http://api.openweathermap.org/data/2.5/weather?q=" + city).request().get(JsonObject.class);
        JsonObjectBuilder jb = Json.createObjectBuilder();
        jb.add("weather", w.getJsonArray("weather").getJsonObject(0));
        jb.add("temp", w.getJsonObject("main").getJsonNumber("temp").intValue() - 273);
        return jb.build();
    }
     
    private String getCurrencyExchangeRate(String cur){
        JsonObject w = ClientBuilder.newClient().target("http://www.apilayer.net/api/live?access_key=9ecdacfb55a9fa164ee7090832421311").request().get(JsonObject.class);
        double dkk = w.getJsonObject("quotes").getJsonNumber("USDDKK").doubleValue();
        double foreing = w.getJsonObject("quotes").getJsonNumber("USD"+cur).doubleValue();
        return ""+(dkk/foreing);
    }
}
