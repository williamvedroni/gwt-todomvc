package me.porcelli.todomvc.client.component;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import me.porcelli.todomvc.client.events.UpdateCount;

@ApplicationScoped
public class TodoListFooterPresenter {

    @Inject
    private TodoListPresenter listPresenter;
    @Inject
    private TodoListFooterView view;

    @PostConstruct
    public void init() {
        view.build();
    }

    public void updateCount( @Observes UpdateCount event ) {
        view.updateCount( event.getCount() );
    }

    public void showAll() {
        listPresenter.showAll();
    }

    public void showOnlyActive() {
        listPresenter.showOnlyActive();
    }

    public void showOnlyCompleted() {
        listPresenter.showOnlyCompleted();
    }

    public void clearCompleted() {
        listPresenter.clearCompleted();
    }

    public IsWidget getView() {
        return view;
    }
}
