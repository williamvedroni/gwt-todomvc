package me.porcelli.todomvc.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO: update me
 */
public class TodoMainView extends Composite {

    interface ViewBinder
            extends
            UiBinder<Widget, TodoMainView> {

    }

    private static ViewBinder uiBinder = GWT.create( ViewBinder.class );

    @UiField
    HTMLPanel app;

    @UiField
    HTMLPanel header;

    public TodoMainView() {
        initWidget( uiBinder.createAndBindUi( this ) );
    }

    public void addNewTodo( IsWidget view ) {
        header.add( view );
    }

    public void add( IsWidget view ) {
        app.add( view );
    }

}
