Use case:
> Define a family of algorithms, encapsulate each one, and make them interchangeable. Client is free to change its strategy.

Real-world example
1. Google Map - User is avail of selecting different strategies to go from the location to the destination via car, bus, walk etc.
2. e-commerce - User can select paying methods of credit card, debit card, gift card. Each strategy encapsulate its own algorithm inside its class.

![Class diagram](https://refactoring.guru/images/patterns/diagrams/strategy/structure.png) 

The context class doesn't know anything about Concrete strategies, but by encapsulating the `Strategy` variable, client is free to change its method and execute the algorithm of their choice.
