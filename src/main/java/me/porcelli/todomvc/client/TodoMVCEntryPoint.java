package me.porcelli.todomvc.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import me.porcelli.todomvc.client.resources.AppResource;

public class TodoMVCEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {

        AppResource.INSTANCE.CSS().ensureInjected();

        final HTMLPanel section = new HTMLPanel( "section", "" );
        section.addStyleName( AppResource.INSTANCE.CSS().todoapp() );

        section.add( buildHeader() );
        section.add( buildSectionMain() );
        section.add( buildFooter() );

        RootPanel.get().add( section );
        RootPanel.get().add( buildPageFooter() );
    }

    private HTMLPanel buildPageFooter() {
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

        final TextBox textBox = new TextBox();
        textBox.getElement().setPropertyString( "placeholder", "What needs to be done?" );
        textBox.addStyleName( AppResource.INSTANCE.CSS().newTodo() );

        header.add( h1 );
        header.add( textBox );

        return header;
    }

    public HTMLPanel buildSectionMain() {
        final HTMLPanel main = new HTMLPanel( "section", "" );
        main.addStyleName( AppResource.INSTANCE.CSS().main() );

        final CheckBox toggleAll = new CheckBox();

        toggleAll.getElement().getFirstChildElement().setClassName( AppResource.INSTANCE.CSS().toggleAll() );

        final HTMLPanel todoList = new HTMLPanel( "ul", "" );
        todoList.addStyleName( AppResource.INSTANCE.CSS().todoList() );

        todoList.add( createTodo( "style todo in pure gwt!", true ) );
        todoList.add( createTodo( "finish demos!", false ) );
        todoList.add( createTodo( "ed asddklsjdfa it", null ) );

        main.add( toggleAll );
        main.add( todoList );

        return main;
    }

    public HTMLPanel buildFooter() {
        final HTMLPanel footer = new HTMLPanel( "footer", "" );
        footer.getElement().getStyle().setDisplay( Style.Display.BLOCK );
        footer.addStyleName( AppResource.INSTANCE.CSS().footer() );

        final InlineLabel todoCount = new InlineLabel( "3 items left" );
        todoCount.addStyleName( AppResource.INSTANCE.CSS().todoCount() );

        footer.add( todoCount );

        final HTMLPanel filters = new HTMLPanel( "ul", "" );
        filters.addStyleName( AppResource.INSTANCE.CSS().filters() );

        {
            final HTMLPanel selected = new HTMLPanel( "li", "" );
            final Anchor allAnchor = new Anchor( "All" );
            allAnchor.addClickHandler( new ClickHandler() {
                @Override
                public void onClick( ClickEvent event ) {
                    Window.alert( "All!" );
                }
            } );

            selected.add( allAnchor );
            filters.add( selected );
        }

        {
            final HTMLPanel selected = new HTMLPanel( "li", "" );
            final Anchor activeAnchor = new Anchor( "Active" );
            activeAnchor.addClickHandler( new ClickHandler() {
                @Override
                public void onClick( ClickEvent event ) {
                    Window.alert( "Active!" );
                }
            } );

            selected.add( activeAnchor );
            filters.add( selected );
        }

        {
            final HTMLPanel selected = new HTMLPanel( "li", "" );
            final Anchor activeAnchor = new Anchor( "Completed" );
            activeAnchor.addClickHandler( new ClickHandler() {
                @Override
                public void onClick( ClickEvent event ) {
                    Window.alert( "Completed!" );
                }
            } );

            selected.add( activeAnchor );
            filters.add( selected );
        }

        footer.add( filters );
        final Button button = new Button( "Clear completed" );
        button.addStyleName( AppResource.INSTANCE.CSS().clearCompleted() );
        footer.add( button );

        return footer;
    }

    public HTMLPanel createTodo( final String todo,
                                 Boolean isCompleted ) {
        final HTMLPanel element = new HTMLPanel( "li", "" );
        final FlowPanel view = new FlowPanel();
        TextBox edit = null;
        view.addStyleName( AppResource.INSTANCE.CSS().view() );

        if ( isCompleted == null ) {
            edit = new TextBox();
            edit.setText( todo );
            edit.addStyleName( AppResource.INSTANCE.CSS().edit() );
            element.addStyleName( AppResource.INSTANCE.CSS().editing() );
        } else if ( isCompleted ) {
            element.addStyleName( AppResource.INSTANCE.CSS().completed() );
        }

        final CheckBox toggle = new CheckBox();

        toggle.getElement().getFirstChildElement().setClassName( AppResource.INSTANCE.CSS().toggle() );
        toggle.setText( todo );

        final Button destroy = new Button();
        destroy.addStyleName( AppResource.INSTANCE.CSS().destroy() );

        view.add( toggle );
        view.add( destroy );
        element.add( view );

        if ( edit != null ) {
            element.add( edit );
        }

        return element;
    }

}
