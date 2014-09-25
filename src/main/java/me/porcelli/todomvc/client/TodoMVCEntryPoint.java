package me.porcelli.todomvc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import me.porcelli.todomvc.client.component.NewTodoPresenter;
import me.porcelli.todomvc.client.component.NewTodoView;
import me.porcelli.todomvc.client.component.TodoListFooterPresenter;
import me.porcelli.todomvc.client.component.TodoListFooterView;
import me.porcelli.todomvc.client.component.TodoListPresenter;
import me.porcelli.todomvc.client.component.TodoListView;
import me.porcelli.todomvc.client.component.TodoMainView;
import me.porcelli.todomvc.client.component.TodoPageFooterView;
import me.porcelli.todomvc.client.resources.AppResource;
import me.porcelli.todomvc.shared.GreetingResponse;
import me.porcelli.todomvc.shared.GreetingService;
import me.porcelli.todomvc.shared.GreetingServiceAsync;

public class TodoMVCEntryPoint implements EntryPoint {

    private NewTodoPresenter newTodoPresenter;
    private TodoListPresenter todoListPresenter;
    private TodoListFooterPresenter todoListFooterPresenter;

    private final GreetingServiceAsync greetingService = GWT.create( GreetingService.class );

    @Override
    public void onModuleLoad() {
        AppResource.INSTANCE.CSS().ensureInjected();

        greetingService.greetServer( "hello", new AsyncCallback<GreetingResponse>() {
            @Override
            public void onFailure( Throwable caught ) {
                Window.alert( "Error!" );
            }

            @Override
            public void onSuccess( GreetingResponse result ) {
                Window.alert( result.toString() );
            }
        } );

        buildObjects();

        final TodoMainView main = new TodoMainView();

        main.addNewTodo( newTodoPresenter.getView() );
        main.add( todoListPresenter.getView() );
        main.add( todoListFooterPresenter.getView() );

        RootPanel.get().add( main );
        RootPanel.get().add( new TodoPageFooterView() );
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
