 LeBlanc’s law: Later equals never.

“one word per concept” *naming*

## Naming 

*clarity is king.*

### Use intention-revealing names

Commonly, we see this
```java
int d; // elasps time
```

Why use comment to explain it while you can give a good naming?

```java
int elapsedTimeInDays;
int daysSinceCreation;
int daysSinceModification;
int fileAgeInDays;
```

### Avoid disinformation

> We should avoid words whose entrenched meanings vary from our intended meaning. 

Do not refer to a group of accounts as `accountList` unless you really want it to be list. What if later on you want to change to `set`? This namespace is sensitive to programmers.

Instead, use:
`accountGroup` or `bunchOfAccounts`, even `accounts`

### Make meaningful distinctions

>Noise words are redundant. The word variable should never appear in a variable name. 

Examples are:
- `Product`, `ProductData`, `ProductInfo`
- `aZoo`, `theZoo`

This below examples are also very common:
- `getActiveAccount();`
- `getActiveAccounts();`
- `getActiveAccountInfo();`

What functions do programmer suppose to call? **Make a meaningful distinctions**. 

### Use pronounceable names 

> Humans are good at words. A significant part of our brains is dedicated to the concept of words.  And words are, by definition, pronounceable. It would be a shame not to take advantage of that huge portion of our brains that has evolved to deal with spoken language.

Bad example:
```java
private Date genymdhms;
```

The variable means generation date, year, month, day, hour, minute, and seconds.

It's hard to pronounce, when we are communicate with another programmer, how do you say this word?

Use:
```java
private Date generationTimestamp;
// more fields
```

### Use Searchable names 
>Single-letter names and numeric constants have a particular problem in that they are not easy to locate across a body of text.

A searchable name can easily to debug and meaningful. 

One condition that you can make use of a single character naming: *single-letter names can ONLY be used as local variables inside short methods. The length of a name should correspond to the size of its scope.*

### Avoid member prefix
> People quickly learn to ignore the prefix (or suffix) to see the meaningful part of the name.

```java
// not this
public class Part{
	private String m_dsc;
}

// try this
public class Part{ 
	private String description;
}
```

### Class names 

> Classes and objects should have noun or noun phrase names.

### Method names 
> Methods should have verb or verb phrase names like `postPayment`.

### Pick One Word per Concept 
> Pick one word for one abstract concept and stick with it.

For instance, it’s confusing to have *fetch, retrieve, and get* as equivalent methods of different classes. 

### Don't pun
> Avoid using the same word for two purposes. Using the same term for two different ideas is essentially a pun.

A word for a concept and stick with it. For example, `add` if in your code means addition of 2 numbers. Then use `add` for that and then. And when you create a collection and want to add the item to that collection, **instead of using `add()`, `insert()` and `append()` is far more appropriate and less confusing.**

Our goal, as authors, is to make our code as easy as possible to understand. We want
our code to be a quick skim, not an intense study.

### Use Solution Domain Names
Remember the people read your codes are programmers. Don't be afraid to name your methods, functions with computer science related naming.

### Add meaningful context

In `Address` class, you might have firstName , lastName, street, houseNumber, city, state, and zipcode. It's easy to understand. But when you're working on another method and you pass in the argument `state`. It's hard to guess it right that which meaning does `state` carries. Instead, use `addrState` when you name the argument in your method.