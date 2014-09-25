package me.porcelli.todomvc.client.component;

import javax.enterprise.context.ApplicationScoped;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

@ApplicationScoped
public class TodoPageFooterView extends Composite {

    interface ViewBinder
            extends
            UiBinder<Widget, TodoPageFooterView> {

    }

    private static ViewBinder uiBinder = GWT.create( ViewBinder.class );

    public TodoPageFooterView() {
        initWidget( uiBinder.createAndBindUi( this ) );
    }

}
