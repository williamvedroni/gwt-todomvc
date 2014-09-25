package me.porcelli.todomvc.client.component;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

@ApplicationScoped
public class TodoMainView extends Composite {

    @Inject
    private NewTodoPresenter newTodoPresenter;
    @Inject
    private TodoListPresenter todoListPresenter;
    @Inject
    private TodoListFooterPresenter todoListFooterPresenter;

    interface ViewBinder
            extends
            UiBinder<Widget, TodoMainView> {

    }

    private static ViewBinder uiBinder = GWT.create( ViewBinder.class );

    @UiField
    HTMLPanel app;

    @UiField
    HTMLPanel header;

    @PostConstruct
    public void init() {
        initWidget( uiBinder.createAndBindUi( this ) );

        header.add( newTodoPresenter.getView() );
        app.add( todoListPresenter.getView() );
        app.add( todoListFooterPresenter.getView() );

    }

}
