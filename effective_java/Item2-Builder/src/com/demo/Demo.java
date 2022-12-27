package com.demo;


public class Demo {
    public static void main(String[] args) throws Exception {

        NewYorkPizza newYorkPizza = new NewYorkPizza.Builder(NewYorkPizza.Size.SMALL)
                .addTopping(Pizza.Topping.SAUSAGE)
                .addTopping(Pizza.Topping.PEPPER)
                .build();
        CalzonePizza calzonePizza = new CalzonePizza.Builder()
                .addTopping(Pizza.Topping.HAM)
                .sauceInside()
                .build();

        TestFacts testObj = new EntityCreator<>(TestFacts.class, 110, 3)
                .setValue("size", 999)
                .setValue("price", 45)
                .build();

        System.out.println(testObj);
    }

}
