`Stack` consider you **pile up** things.

Usage example
```java
Stack<String> game = new Stack<String>();
game.add("Call of Duty");
game.add("Pokemon");
game.add("Runner");

System.out.println(game); // ["Call of duty", "Pokemon", "Runner"]

game.peek(); // this will give you "the toppest" element - "Runner"
game.pop(); // will remove and pop out "Runner"
```
* `game.containts("Pokemon")` return boolean
* `game.set(1, "newGame")` change the index 1 to "newGame"
* `game.get(1)` get the index 1 element
* `game.isEmpty()` return boolean
* `game.size()` return length
