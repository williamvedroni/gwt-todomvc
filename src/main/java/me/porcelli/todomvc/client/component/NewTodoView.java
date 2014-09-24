package me.porcelli.todomvc.client.component;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import me.porcelli.todomvc.client.resources.AppResource;

public class NewTodoView implements IsWidget {

    private NewTodoPresenter presenter;
    private TextBox textBox;

    public void init( final NewTodoPresenter presenter ) {
        this.presenter = presenter;
    }

    public void build() {
        textBox = new TextBox();
        textBox.getElement().setPropertyString( "placeholder", "What needs to be done?" );
        textBox.addStyleName( AppResource.INSTANCE.CSS().newTodo() );
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

    @Override
    public Widget asWidget() {
        return textBox;
    }
}
