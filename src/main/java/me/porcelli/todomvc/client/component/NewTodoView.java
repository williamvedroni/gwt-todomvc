package me.porcelli.todomvc.client.component;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

@Dependent
public class NewTodoView extends Composite {

    interface ViewBinder
            extends
            UiBinder<Widget, NewTodoView> {

    }

    private static ViewBinder uiBinder = GWT.create( ViewBinder.class );

    @UiField
    TextBox textBox;

    @Inject
    private NewTodoPresenter presenter;

    @PostConstruct
    void init() {
        initWidget( uiBinder.createAndBindUi( this ) );
    }

    public void build() {
        textBox.getElement().setPropertyString( "placeholder", "What needs to be done?" );
        textBox.addKeyPressHandler( new KeyPressHandler() {
            @Override
            public void onKeyPress( KeyPressEvent event ) {

                boolean enterPressed = KeyCodes.KEY_ENTER == event.getNativeEvent().getKeyCode();
                if ( enterPressed && !textBox.getText().trim().isEmpty() ) {
                    presenter.addNewTodo( textBox.getText() );
                    textBox.setText( "" );
                }
            }
        } );
    }
}
