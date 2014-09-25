package me.porcelli.todomvc.client.service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IdGeneratorService {

    private int last = -1;

    public int getNext() {
        return ++last;
    }

}
