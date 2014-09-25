package me.porcelli.todomvc.client.component;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Widget;
import me.porcelli.todomvc.client.resources.AppResource;

@Dependent
public class TodoListFooterView extends Composite {

    interface ViewBinder
            extends
            UiBinder<Widget, TodoListFooterView> {

    }

    private static ViewBinder uiBinder = GWT.create( ViewBinder.class );

    @UiField
    HTMLPanel footer;

    @UiField
    InlineLabel todoCount;

    @UiField
    Anchor allAnchor;

    @UiField
    Anchor activeAnchor;

    @UiField
    Anchor completedAnchor;

    @UiField
    Button clearCompleted;

    @Inject
    private TodoListFooterPresenter presenter;

    @PostConstruct
    void init() {
        initWidget( uiBinder.createAndBindUi( this ) );
    }

    public void updateCount( int count ) {
        if ( count < 0 ) {
            todoCount.setText( "" );
        } else if ( count == 1 ) {
            todoCount.getElement().setInnerHTML( "<strong>1</strong> task left" );
        } else {
            todoCount.getElement().setInnerHTML( "<strong>" + count + "</strong> tasks left" );
        }
    }

    public void build() {
        allAnchor.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.showAll();
                allAnchor.addStyleName( AppResource.INSTANCE.CSS().selected() );
                activeAnchor.removeStyleName( AppResource.INSTANCE.CSS().selected() );
                completedAnchor.removeStyleName( AppResource.INSTANCE.CSS().selected() );
            }
        } );

        activeAnchor.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.showOnlyActive();
                activeAnchor.addStyleName( AppResource.INSTANCE.CSS().selected() );
                allAnchor.removeStyleName( AppResource.INSTANCE.CSS().selected() );
                completedAnchor.removeStyleName( AppResource.INSTANCE.CSS().selected() );
            }
        } );

        completedAnchor.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                completedAnchor.addStyleName( AppResource.INSTANCE.CSS().selected() );
                allAnchor.removeStyleName( AppResource.INSTANCE.CSS().selected() );
                activeAnchor.removeStyleName( AppResource.INSTANCE.CSS().selected() );
                presenter.showOnlyCompleted();
            }
        } );

        clearCompleted.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.clearCompleted();
            }
        } );
    }
}
