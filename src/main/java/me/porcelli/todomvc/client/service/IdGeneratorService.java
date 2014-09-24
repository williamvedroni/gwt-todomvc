package me.porcelli.todomvc.client.service;

public class IdGeneratorService {

    private int last = -1;

    public int getNext() {
        return ++last;
    }

}
