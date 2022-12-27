Example in plain words
> Define a list of all the dependents in the class, and whenever there's a change, we will notify each dependents and update the state.

## Intent
> Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and update automatically.

![Class Diagram](https://java-design-patterns.com/patterns/observer/etc/observer.png) 

We create an interface `Observer` with `update(EnumType type)`, and implementations are the ones we want to notify/update. The `WeatherType` is the enum type in this case. The `Weather` class keeps a list of the observers and whenever a state change happens, it'll update the `List<Oberver>`.
