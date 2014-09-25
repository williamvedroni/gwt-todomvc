package me.porcelli.todomvc.shared;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("test.php")
public interface LastIdService {

    @GET
    @Produces("text/plain")
    public String getLastId();

}
