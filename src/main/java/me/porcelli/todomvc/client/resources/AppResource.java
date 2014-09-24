package me.porcelli.todomvc.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;

public interface AppResource
        extends
        ClientBundle {

    AppResource INSTANCE = GWT.create( AppResource.class );

    @Source("images/bg.png")
    DataResource bg();

    @Source("css/style.css")
    StyleCss CSS();

}
