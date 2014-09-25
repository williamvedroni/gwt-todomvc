package me.porcelli.todomvc.client;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.google.gwt.user.client.ui.RootPanel;
import me.porcelli.todomvc.client.component.TodoMainView;
import me.porcelli.todomvc.client.component.TodoPageFooterView;
import me.porcelli.todomvc.client.resources.AppResource;
import org.jboss.errai.ioc.client.api.EntryPoint;

@EntryPoint
public class TodoMVC {

    @Inject
    private TodoMainView view;

    @Inject
    private TodoPageFooterView info;

    @PostConstruct
    public void init() {
        AppResource.INSTANCE.CSS().ensureInjected();

        RootPanel.get().add( view );
        RootPanel.get().add( info );
    }
}
