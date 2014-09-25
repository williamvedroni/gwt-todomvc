package me.porcelli.todomvc.client.events;

public class UpdateCount {

    private final int count;

    public UpdateCount( int count ) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
