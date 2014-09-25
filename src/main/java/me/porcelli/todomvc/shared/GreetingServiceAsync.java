package me.porcelli.todomvc.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

    void greetServer( final String input,
                      final AsyncCallback<GreetingResponse> callback )
            throws IllegalArgumentException;
}
