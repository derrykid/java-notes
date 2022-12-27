## Adapter intention

Real-world example
> A three-legged plug can't be connected to a two-pronged outlet, it needs a power adapter that makes it compatible.

Programming example explain
> We have a captain who only knows how to row a boat, he can't sail. One day, he sees pirates are coming, he must learn how to sail, otherwise he will be dead meat. How can he know how to sail right now? We make an adapter for him.

![Class diagram](https://java-design-patterns.com/patterns/adapter/etc/adapter.urm.png) 

In the diagram, captain knows how to use `RowingBoat` and its implementation class. The `FishingBoat` is completely another thing. Now, we create an **adapter** named `FishingBoatAdapter` class either **extends the class** or **implements the interface**. Then the captain can create an instance of `FishingBoatAdapter` and use the `FishingBoat` right now.

In `FishingBoatAdapter`, we create an instance final variable `FishngBoat`, and implements `RowingBoat`, so it's a kind of `Rowing Boat` right now.
```java
public class FishingBoatAdapter implements RowingBoat{
    
    private final FishingBoat boat;
    
    public FishingBoatAdapter() {
        boat = new FishingBoat();
    }
    
    @Override
    public void row(){
        // the fishing boat methods
        boat.sail();
    }
    
}
```


## When to use
- When an outside component provides captivating functionality that we'd like to reuse, but it's incompatible with our current application. A suitable Adapter can be developed to make them compatible with each other
- When our application is not compatible with the interface that our client is expecting
- When we want to reuse legacy code in our application without making any modification in the original code
