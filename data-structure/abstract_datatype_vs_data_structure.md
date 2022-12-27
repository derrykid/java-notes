# Abstract data types
Abstract data type is *what are the expected behaviours from the use point of view.* 

It's the interface that is defined.

For example, in Java, we expect `Collection` to be able to collect data.

# Data structures
Data structures is *how the behaviours will be exhibited.* 

It's the implementation.

# Example:
These concepts aren't black and white which is the opposite to each other. They are blueprint and the building.

In Java, a list can contain a list of objects, `List` here is the interface, the behaviour we want. This `List` will have these methods:

- `add()`
- `get()`
- `remove()`

This is what we exepct from the list.

And the **implementation, or we say the data structure is**:
- ArrayList
- LinkedList

---
Same apply to Map, we want a key value pair that we can do:
- `get(key): return v`

This is the abstract data types, or the interface we speak, the implementation/data structure that implements it are:
- `HashMap`
- `TreeMap`

