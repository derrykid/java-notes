package com.demo;


public class TestFacts {
    private final int servingSize;
    private final int servings;
    private int size;
    private int price;

    private TestFacts(int servingSize, int servings){
        this.servingSize = servingSize;
        this.servings = servings;
    }

    @Override
    public String toString() {
        return "TestFacts{" +
                "servingSize=" + servingSize +
                ", servings=" + servings +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
