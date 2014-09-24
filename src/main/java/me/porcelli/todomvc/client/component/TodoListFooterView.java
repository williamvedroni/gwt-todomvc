package me.porcelli.todomvc.client.component;

import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import me.porcelli.todomvc.client.resources.AppResource;

/**
 * TODO: update me
 */
public class TodoListFooterView implements IsWidget {

    private HTMLPanel footer;
    private InlineLabel todoCount;
    private TodoListFooterPresenter presenter;

    public void init( final TodoListFooterPresenter presenter ) {
        this.presenter = presenter;
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
        footer = new HTMLPanel( "footer", "" );
        footer.getElement().getStyle().setDisplay( Style.Display.BLOCK );
        footer.addStyleName( AppResource.INSTANCE.CSS().footer() );

        todoCount = new InlineLabel();
        todoCount.addStyleName( AppResource.INSTANCE.CSS().todoCount() );

        footer.add( todoCount );

        final HTMLPanel filters = new HTMLPanel( "ul", "" );
        filters.addStyleName( AppResource.INSTANCE.CSS().filters() );

        final Anchor allAnchor = new Anchor( "All" );
        final Anchor activeAnchor = new Anchor( "Active" );
        final Anchor completedAnchor = new Anchor( "Completed" );

        allAnchor.addStyleName( AppResource.INSTANCE.CSS().selected() );

        final HTMLPanel allLi = new HTMLPanel( "li", "" );
        allAnchor.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.showAll();
                allAnchor.addStyleName( AppResource.INSTANCE.CSS().selected() );
                activeAnchor.removeStyleName( AppResource.INSTANCE.CSS().selected() );
                completedAnchor.removeStyleName( AppResource.INSTANCE.CSS().selected() );
            }
        } );

        allLi.add( allAnchor );
        filters.add( allLi );

        final HTMLPanel activeLi = new HTMLPanel( "li", "" );
        activeAnchor.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.showOnlyActive();
                activeAnchor.addStyleName( AppResource.INSTANCE.CSS().selected() );
                allAnchor.removeStyleName( AppResource.INSTANCE.CSS().selected() );
                completedAnchor.removeStyleName( AppResource.INSTANCE.CSS().selected() );
            }
        } );

        activeLi.add( activeAnchor );
        filters.add( activeLi );

        final HTMLPanel completedLi = new HTMLPanel( "li", "" );
        completedAnchor.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                completedAnchor.addStyleName( AppResource.INSTANCE.CSS().selected() );
                allAnchor.removeStyleName( AppResource.INSTANCE.CSS().selected() );
                activeAnchor.removeStyleName( AppResource.INSTANCE.CSS().selected() );
                presenter.showOnlyCompleted();
            }
        } );

        completedLi.add( completedAnchor );
        filters.add( completedLi );

        footer.add( filters );
        final Button button = new Button( "Clear completed" );
        button.addStyleName( AppResource.INSTANCE.CSS().clearCompleted() );
        button.addClickHandler( new ClickHandler() {
            @Override
            public void onClick( ClickEvent event ) {
                presenter.clearCompleted();
            }
        } );
        footer.add( button );
    }

    @Override
    public Widget asWidget() {
        return footer;
    }
}
