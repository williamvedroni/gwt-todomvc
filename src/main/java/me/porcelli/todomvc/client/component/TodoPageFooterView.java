package me.porcelli.todomvc.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 * TODO: update me
 */
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
