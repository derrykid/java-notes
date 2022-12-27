> If there's a RPG game, and there are different professions, genders, hair colors, etc. Best way is to let the computer create the character for you. 

Builder pattern is a solution to the telescoping constructor anti-pattern. Which you don't have to see a overload constructors with every instance variables.


[java-design pattern](https://java-design-patterns.com/patterns/builder/) 

[refactoring guru](https://refactoring.guru/design-patterns/builder) 

##### Note

- `director` is not necessary
- It's usually client's responsibility to reset the builder, unlikely we have to create the reset methods
