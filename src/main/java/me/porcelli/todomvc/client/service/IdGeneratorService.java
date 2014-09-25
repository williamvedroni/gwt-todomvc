package me.porcelli.todomvc.client.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.user.client.Window;
import me.porcelli.todomvc.shared.LastIdService;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.enterprise.client.jaxrs.api.RestClient;
import org.jboss.errai.ioc.client.api.AfterInitialization;

@ApplicationScoped
public class IdGeneratorService {

    @Inject
    private Caller<LastIdService> lastIdService;

    private int last = -1;

    @AfterInitialization
    public void init() {
        RestClient.setApplicationRoot( "http://www.random.org/" );
        lastIdService.call( new RemoteCallback<String>() {
            @Override
            public void callback( String result ) {
                result = result.replaceAll( "\n", "" );
                Window.alert( "last_id: " + result );
                last = Integer.valueOf( result );
            }
        } ).getLastId();
    }

    public int getNext() {
        return ++last;
    }

}
