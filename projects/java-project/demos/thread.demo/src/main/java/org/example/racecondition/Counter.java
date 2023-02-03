package org.example.racecondition;

public class Counter {

    private int count;

    public int inc(){
        count++;
        return count;
    }

    public int getCount(){
        return this.count;
    }

}
