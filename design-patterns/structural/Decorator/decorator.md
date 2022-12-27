## Intention

> Attach additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality

## Common pitfall

> It's easy to think that the way to implement it is `extends` the target class, while it actually might get heavily dependent on the super class. Create another class will be a better design. Tho still have implementations that use `extends`


### Programming example

We have a troll, it has simple behaviours: `attack`, `gainAttackPower`, `flee`. After a while, the troll gains a lot of battle experience, he now can evolve and be a warrior troll.

## Class diagram
![Class diagram](https://java-design-patterns.com/patterns/decorator/etc/decorator.urm.png) 
