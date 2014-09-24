package me.porcelli.todomvc.client.component;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import me.porcelli.todomvc.client.model.Status;
import me.porcelli.todomvc.client.resources.AppResource;

/**
 * TODO: update me
 */
public class TodoElementView implements TodoElementPresenter.View {

    private TodoElementPresenter presenter;
    private HTMLPanel element;
    private TextBox edit;
    private HTMLPanel label;
    private Status stateBeforeEdit;
    private CheckBox toggle;

    public void init( final TodoElementPresenter presenter ) {
        this.presenter = presenter;
    }

    public void build() {
        this.element = new HTMLPanel( "li", "" );
        this.stateBeforeEdit = presenter.getStatus();
        final FlowPanel view = new FlowPanel();
        edit = new TextBox();
        edit.addStyleName( AppResource.INSTANCE.CSS().edit() );
        edit.setVisible( false );
        view.addStyleName( AppResource.INSTANCE.CSS().view() );

        toggle = new CheckBox();
        toggle.getElement().getFirstChildElement().setClassName( AppResource.INSTANCE.CSS().toggle() );
        toggle.getElement().removeChild( toggle.getElement().getLastChild() );
        label = new HTMLPanel( "label", "" );
        label.getElement().setInnerText( presenter.getTask() );

        final Button destroy = new Button();
        destroy.addStyleName( AppResource.INSTANCE.CSS().destroy() );

        view.add( toggle );
        view.add( label );
        view.add( destroy );
        element.add( view );

        if ( edit != null ) {
            element.add( edit );
        }

        element.addDomHandler( new DoubleClickHandler() {
            @Override
            public void onDoubleClick( final DoubleClickEvent event ) {
                presenter.setStatus( Status.EDIT );
                setStatus( Status.EDIT );
            }
        }, DoubleClickEvent.getType() );

        toggle.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( final ClickEvent event ) {
                if ( toggle.getValue() != null && toggle.getValue() ) {
                    presenter.setStatus( Status.COMPLETED );
                    setStatus( Status.COMPLETED );
                } else {
                    presenter.setStatus( Status.ACTIVE );
                    setStatus( Status.ACTIVE );
                }
            }
        } );

        destroy.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( final ClickEvent event ) {
                presenter.delete();
            }
        } );

        edit.addKeyPressHandler( new KeyPressHandler() {
            @Override
            public void onKeyPress( KeyPressEvent event ) {

                boolean enterPressed = KeyCodes.KEY_ENTER == event.getNativeEvent().getKeyCode();
                if ( enterPressed ) {
                    label.getElement().setInnerText( edit.getText() );
                    presenter.setTask( edit.getText() );
                    presenter.setStatus( stateBeforeEdit );
                    setStatus( stateBeforeEdit );
                }
            }
        } );

        setStatus( presenter.getStatus() );
    }

    @Override
    public Widget asWidget() {
        return element;
    }

    public void setStatus( final Status status ) {
        switch ( status ) {
            case COMPLETED:
                toggle.setValue( true );
                edit.setVisible( false );
                element.addStyleName( AppResource.INSTANCE.CSS().completed() );
                element.removeStyleName( AppResource.INSTANCE.CSS().editing() );
                break;
            case ACTIVE:
                toggle.setValue( false );
                edit.setVisible( false );
                element.removeStyleName( AppResource.INSTANCE.CSS().completed() );
                element.removeStyleName( AppResource.INSTANCE.CSS().editing() );
                break;
            case EDIT:
                edit.setVisible( true );
                edit.setText( presenter.getTask() );
                element.removeStyleName( AppResource.INSTANCE.CSS().completed() );
                element.addStyleName( AppResource.INSTANCE.CSS().editing() );
                break;
        }
    }

    @Override
    public void hide() {
        element.setVisible( false );
    }

    @Override
    public void show() {
        element.setVisible( true );
    }
}
