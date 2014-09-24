package me.porcelli.todomvc.client.component;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import me.porcelli.todomvc.client.resources.AppResource;

public class TodoListView implements IsWidget {

    private HTMLPanel todoList;
    private TodoListPresenter presenter;
    private HTMLPanel main;

    public void init( final TodoListPresenter presenter ) {
        this.presenter = presenter;
    }

    public void build() {
        main = new HTMLPanel( "section", "" );
        main.addStyleName( AppResource.INSTANCE.CSS().main() );

        final CheckBox toggleAll = new CheckBox();

        toggleAll.getElement().getFirstChildElement().setClassName( AppResource.INSTANCE.CSS().toggleAll() );

        todoList = new HTMLPanel( "ul", "" );
        todoList.addStyleName( AppResource.INSTANCE.CSS().todoList() );

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

        main.add( toggleAll );
        main.add( todoList );
    }

    public void addTodo( final IsWidget view ) {
        todoList.add( view );
    }

    @Override
    public Widget asWidget() {
        return main;
    }
}
