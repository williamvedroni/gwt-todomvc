package me.porcelli.todomvc.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import me.porcelli.todomvc.shared.GreetingResponse;
import me.porcelli.todomvc.shared.GreetingService;

/**
 * The server side implementation of the RPC service.
 */
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    public GreetingResponse greetServer( String input ) throws IllegalArgumentException {
        GreetingResponse response = new GreetingResponse();

        response.setServerInfo( getServletContext().getServerInfo() );
        response.setUserAgent( getThreadLocalRequest().getHeader( "User-Agent" ) );

        response.setGreeting( "Hello, " + input + "!" );

        return response;
    }
}
