package me.porcelli.todomvc.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class NewTodoView extends Composite {

    interface ViewBinder
            extends
            UiBinder<Widget, NewTodoView> {

    }

    @UiField
    TextBox textBox;

    public NewTodoView() {
        initWidget( uiBinder.createAndBindUi( this ) );
    }

    private static ViewBinder uiBinder = GWT.create( ViewBinder.class );

    private NewTodoPresenter presenter;

    public void init( final NewTodoPresenter presenter ) {
        this.presenter = presenter;
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
