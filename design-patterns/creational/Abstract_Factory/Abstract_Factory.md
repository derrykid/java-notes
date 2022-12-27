> A factory of factories. A factoryMaker that groups the individual but related factories together without specifying their concrete classes.

![Class diagram](https://java-design-patterns.com/patterns/abstract-factory/etc/abstract-factory.urm.png) 

Kingdom has different kinds: elf, orc, etc. Make a `enum` class of these.

We want to create a kingdom. A kingdom consists of 3 elements: `King`, `Castle`, `Army`. We make interfaces of these.

Therefore, we make a kingdom factory `interface` which can creates `King`, `Castle`, and `Army`.

In order to manage different types of kingdom factories, we create a `factoryMaker class`. Client can create a factory by passing enum value, e.g. `KingdomType.ELF` to the `factoryMaker.makeFactory()`, therefore the factory specific to a race is created.
