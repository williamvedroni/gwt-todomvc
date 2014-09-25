package me.porcelli.todomvc.client.component;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import me.porcelli.todomvc.client.resources.AppResource;

public class TodoListView extends Composite implements IsWidget {

    interface ViewBinder
            extends
            UiBinder<Widget, TodoListView> {

    }

    private static ViewBinder uiBinder = GWT.create( ViewBinder.class );

    @UiField
    HTMLPanel main;

    @UiField
    CheckBox toggleAll;

    @UiField
    HTMLPanel todoList;

    private TodoListPresenter presenter;

    public TodoListView() {
        initWidget( uiBinder.createAndBindUi( this ) );
    }

    public void init( final TodoListPresenter presenter ) {
        this.presenter = presenter;
    }

    public void build() {
        toggleAll.getElement().getFirstChildElement().setClassName( AppResource.INSTANCE.CSS().toggleAll() );

        toggleAll.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( final ClickEvent event ) {
                if ( toggleAll.getValue() ) {
                    presenter.selectAll();
                } else {
                    presenter.unSelectAll();
                }

            }
        } );
    }

    public void addTodo( final IsWidget view ) {
        todoList.add( view );
    }
}
