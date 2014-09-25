package me.porcelli.todomvc.client.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import me.porcelli.todomvc.client.events.AddTodo;
import me.porcelli.todomvc.client.events.DeleteTodo;
import me.porcelli.todomvc.client.model.Todo;

@ApplicationScoped
public class StorageService {

    @Inject
    private EntityManager em;

    public void save( @Observes final AddTodo event ) {
        em.merge( event.getTodo() );
        em.flush();
        em.detach( event.getTodo() );

        Todo fetchedTask = em.find( Todo.class, event.getTodo().getId() );
    }

    public void delete( @Observes final DeleteTodo event ) {
        em.remove( event.getTodo() );
        em.flush();

        Todo fetchedTask = em.find( Todo.class, event.getTodo().getId() );
    }

}
