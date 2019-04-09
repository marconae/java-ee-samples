package de.nae.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("health")
public class HealthResource {

    @GET
    public Boolean isHealth() {
        return true;
    }

}
