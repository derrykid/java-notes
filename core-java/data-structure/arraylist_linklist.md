ArrayList and LinkedList both **implements List interface and maintains insertion order.** Both are non synchronized classes.

Difference
| ArrayList                                                                               | LinkedList                                                                                          |
|-----------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|
| Internally use a **dynamic array** to store the elements                                | use a **doubly linked list** to store the element                                                   |
| Manipulation is **slow**. If any element is removed, all the bits are shifted in memory | Manipulation is faster because the doubly linked list. **no bit shifting is required when removed** |
| It acts **as a list only because it implements List.**                                  | **Act as a list and queue** because it implements List and Deque interfaces                         |
| Better for storing and accessing data                                                   | Better for manipulating data                                                                        |
