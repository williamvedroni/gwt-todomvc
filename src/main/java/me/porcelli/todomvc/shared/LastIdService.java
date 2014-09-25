package me.porcelli.todomvc.shared;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("integers/?num=1&min=1&max=100000&col=1&base=10&format=plain&rnd=new")
public interface LastIdService {

    @GET
    @Produces("text/plain")
    @Consumes("text/plain")
    public String getLastId();

}
