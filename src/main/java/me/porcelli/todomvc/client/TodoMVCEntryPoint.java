package me.porcelli.todomvc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import me.porcelli.todomvc.client.component.NewTodoPresenter;
import me.porcelli.todomvc.client.component.NewTodoView;
import me.porcelli.todomvc.client.component.TodoListFooterPresenter;
import me.porcelli.todomvc.client.component.TodoListFooterView;
import me.porcelli.todomvc.client.component.TodoListPresenter;
import me.porcelli.todomvc.client.component.TodoListView;
import me.porcelli.todomvc.client.resources.AppResource;

public class TodoMVCEntryPoint implements EntryPoint {

    private NewTodoPresenter newTodoPresenter;
    private TodoListPresenter todoListPresenter;
    private TodoListFooterPresenter todoListFooterPresenter;

    @Override
    public void onModuleLoad() {
        AppResource.INSTANCE.CSS().ensureInjected();

        buildObjects();

        final HTMLPanel appArea = new HTMLPanel( "section", "" );
        appArea.addStyleName( AppResource.INSTANCE.CSS().todoapp() );

        appArea.add( buildHeader() );
        appArea.add( todoListPresenter.getView() );
        appArea.add( todoListFooterPresenter.getView() );

        RootPanel.get().add( appArea );
        RootPanel.get().add( buildFooter() );
    }

    private HTMLPanel buildFooter() {
        final HTMLPanel footer = new HTMLPanel( "footer", "" );
        footer.addStyleName( AppResource.INSTANCE.CSS().info() );

        final HTMLPanel p1 = new HTMLPanel( "p", "" );
        p1.getElement().setInnerText( "Double-click to edit a todo" );

        final HTMLPanel p2 = new HTMLPanel( "p", "" );
        p2.getElement().setInnerText( "Created by Alexandre Porcelli" );

        footer.add( p1 );
        footer.add( p2 );

        return footer;
    }

    public HTMLPanel buildHeader() {
        final HTMLPanel header = new HTMLPanel( "header", "" );
        header.addStyleName( AppResource.INSTANCE.CSS().header() );

        final HTMLPanel h1 = new HTMLPanel( "h1", "" );
        h1.getElement().setInnerText( "todos" );

        header.add( h1 );
        header.add( newTodoPresenter.getView() );

        return header;
    }

    private void buildObjects() {
        todoListPresenter = new TodoListPresenter();
        final TodoListView todoListView = new TodoListView();
        todoListView.init( todoListPresenter );

        newTodoPresenter = new NewTodoPresenter();
        final NewTodoView newTodoView = new NewTodoView();
        newTodoView.init( newTodoPresenter );

        todoListFooterPresenter = new TodoListFooterPresenter();
        final TodoListFooterView todoListFooterView = new TodoListFooterView();
        todoListFooterView.init( todoListFooterPresenter );

        newTodoPresenter.init( todoListPresenter, newTodoView );
        todoListPresenter.init( todoListView, todoListFooterPresenter );
        todoListFooterPresenter.init( todoListPresenter, todoListFooterView );
    }
}
