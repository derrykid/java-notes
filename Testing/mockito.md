[toc]
# Mockito

## Important annotations

> To enable Mockito annotations (such as `@Spy, @Mock`, … ), we need to use `@ExtendWith(MockitoExtension.class)` that initializes mocks and handles strict stubbings.


| Annotation                          | Description                                  |
|-------------------------------------|----------------------------------------------|
| @ExtendWith(MockitoExtension.class) | run with JUnit                               |
| @Mock                               | mockito creates a mock object of this        |
| @InjectMocks                        | inject the *@Mock* object into this instance |

## `Mockito` integrate with `JUnit`

```java
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@ExtendWith(MockitoExtension.class)
public class MathApplicationTester {
	
   //@InjectMocks annotation is used to create and inject the mock object
   @InjectMocks 
   MathApplication mathApplication = new MathApplication();

   //@Mock annotation is used to create the mock object to be injected
   @Mock
   CalculatorService calcService;

   @Test
   public void testAdd(){
      //add the behavior of calc service to add two numbers
      when(calcService.add(10.0,20.0)).thenReturn(30.00);
		
      //test the add functionality
      Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
   }
}
```

# Dependency

Only use mockito
```
testImplementation 'org.mockito:mockito-core:4.11.0'
```

Work with JUnit
```
// maven
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.8.1</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>4.6.1</version>
    <scope>test</scope>
</dependency>


// gradle
testImplementation 'org.mockito:mockito-core:4.11.0'    
testImplementation 'org.mockito:mockito-junit-jupiter:4.11.0'
```

`mockito-inline` package is the Java 16 ready version of `mockito-core`. It also support mocking of static methods and final classes. `mockito-junit-jupiter` add the `MockitoExtension` for JUnit 5.

# Writing tests

Pattern
```java
when(object.someMethod(x, y)).then(z);

Assertions.assertEquals(object.someMethod(x, y), z);
```

Or make use of BDD
```java
import static org.mockito.BDDMockito.*;

@Test
public void testAdd(){

  //Given
  given(calcService.add(20.0,10.0)).willReturn(30.0);

  //when
  double result = calcService.add(20.0,10.0);

  //then
  Assert.assertEquals(result,30.0,0);   
}

```

## Create mocks - mock interface, or POJO

### By annotations `@Mock`, and inject with `@InjectMocks`

Create the mock objects and inject
```java
class TesterMockito {

    @InjectMocks 
    MathApplication mathApplication = new MathApplication();

    @Mock
    CalculatorService calcService;

    // other tests
}
```

### By `mock(...)`

Syntax: `service = mock(Clazz.class)`

```java
public class MathApplicationTester {
	
   private MathApplication mathApplication;
   private CalculatorService calcService;

   @Before
   public void setUp(){
      mathApplication = new MathApplication();
      calcService = mock(CalculatorService.class);
      mathApplication.setCalculatorService(calcService);
   }

   // tests
}
```

## Base classes for following examples

These example classes will server as the base for the following examples. I will only use `JUnit` assertions API in these examples.
```java
// service interface
public interface CalculatorService {
   double add(double input1, double input2);
   double subtract(double input1, double input2);
   double multiply(double input1, double input2);
   double divide(double input1, double input2);
}

// app
public class MathApplication {
   private CalculatorService calcService;

   public void setCalculatorService(CalculatorService calcService){
      this.calcService = calcService;
   }
   
   public double add(double input1, double input2){
      return calcService.add(input1, input2);
   }
   
   public double subtract(double input1, double input2){
      return calcService.subtract(input1, input2);
   }
   
   public double multiply(double input1, double input2){
      return calcService.multiply(input1, input2);
   }
   
   public double divide(double input1, double input2){
      return calcService.divide(input1, input2);
   }
}
```


## `when(...).then...` - stubbing

> When calling the method `when(...)`, then do the defined behaviour in `thenXXX(...)`.

Stub is a mock behaviour. In the testing, we test a method calling the remote API, which if it takes 20 seconds for response, we can't wait that long. Therefore, we can *fake/mock* the behaviour.

```java
when(calcService.add(10.0,20.0)).thenReturn(30.00);

Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
```

### use `doXXX().when` or `when().someMethod()` ?

Sometimes the `when(object.someMethod()).thenXXX()` is preferred. 

But according to Mockito documentation, compiler does not like something return void. 

So use `doThrow()...` or any similar is good then. 

Read the API documentation prompt in IDE when implementing it is good idea.


## `verify()` - verify a method is being called

```java
// perform the behaviour
Assert.assertEquals(calcService.add(10.0, 20.0),30.0,0);

//verify call to calcService is made or not with same arguments.
verify(calcService).add(10.0, 20.0);
```

### `verify(Object, <Times>)` - check the number of times a method calls

Syntax: `verify(Object, times()).methodCall`

- The default count is 1. so omit `times()` argument
- `times(int t)` - expect t calls
- `never()` - never called
- `atLeast(int min)` - expects min calls.
- `atLeastOnce()` - expects at least one call.
- `atMost(int max)` - expects max calls.

```java
//limit the method call to 1, no less and no more calls are allowed
verify(calcService).add(10.0, 20.0);

// equivalent to the following
verify(calcService, times(1)).add(10.0, 20.0);
```

Example:
```java
when(calcService.add(10.0,20.0)).thenReturn(30.00);
when(calcService.subtract(20.0,10.0)).thenReturn(10.00);

Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);
Assert.assertEquals(mathApplication.add(10.0, 20.0),30.0,0);

Assert.assertEquals(mathApplication.subtract(20.0, 10.0),10.0,0.0);

//check if add function is called three times
verify(calcService, times(3)).add(10.0, 20.0);

//default call count is 1 
verify(calcService).subtract(20.0, 10.0);

//verify that method was never called on a mock
verify(calcService, never()).multiply(10.0,20.0);
```

### `timeout(long millis)` - check the method stipulate time frame

```java
//passes when add() is called within 100 ms.
verify(calcService,timeout(100)).add(20.0,10.0);

// it can also be used with times()
verify(calcService, timeout(100).times(1)).subtract(20.0,10.0);
```

##  handling exception

There are 2 ways to test exception handling

```java
// use doThrow()
doThrow(Exception ex).when(Object).methodCall();

// or when
when(object.someMethod).thenThrow(exception);
```

```java
//add the behavior to throw exception
doThrow(new RuntimeException("Exception")).when(calcService).add(3, 0);

assertThrows(RuntimeException.class, () -> calculatorService.divide(3, 0));
```

## `InOrder` class - to test the order of execution

> Mockito provides `Inorder` class which takes care of the order of method calls that the mock will make in its action.

```java
//create an inOrder verifier for a single mock
InOrder inOrder = inOrder(calcService);

//following will make sure that add is first called then subtract is called.
inOrder.verify(calcService).add(20.0,10.0);
inOrder.verify(calcService).subtract(20.0,10.0);
```

Test example:
```java
@Test
public void testAddAndSubtract(){

    when(calcService.add(20.0,10.0)).thenReturn(30.0);
    when(calcService.subtract(20.0,10.0)).thenReturn(10.0);

    Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);
    Assert.assertEquals(mathApplication.subtract(20.0, 10.0),10.0,0);


    // test does not pass because it's wrong order
    InOrder inOrder = inOrder(calcService);
    inOrder.verify(calcService).subtract(20.0,10.0);
    inOrder.verify(calcService).add(20.0,10.0);
}
```

## `Answer()` - allows stubbing with generic interface.

Syntax:
```java
when(calcService.add(20.0,10.0)).thenAnswer(new Answer<Double>() {
   @Override
   public Double answer(InvocationOnMock invocation) throws Throwable {
      //get the arguments passed to mock
      Object[] args = invocation.getArguments();
      //get the mock 
      Object mock = invocation.getMock();	
      //return the result
      return 30.0;
   }
});
```

Example:
```java
when(calcService.add(20.0,10.0)).thenAnswer(new Answer<Double>() {

 @Override
 public Double answer(InvocationOnMock invocation) throws Throwable {
    //get the arguments passed to mock
    Object[] args = invocation.getArguments();
    
    //get the mock 
    Object mock = invocation.getMock();	
    
    //return the result
    return 30.0;
 }
});

Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);
```

Replace by lambda:
```java
when(calculatorService.add(10, 10)).thenAnswer(invocation -> 30.0);
```

## `spy()`

[Baeldung](https://www.baeldung.com/mockito-spy) 

> Mockito creates spy on real objects. When spy is called, then actual method of real object is called.

You create a real object, then you wrap that object with Mockito wrapper so as to perform Mockito API. Most of time, you just create a mock object. That's just bare-bones shell instance. So then you add behaviour like `when(...).thenXXX()`.

Create spy by:
```java
@Spy
List<String> spyList = new ArrayList<String>();

// or use wrapper
List<String> list = new ArrayList<String>();
List<String> spyList = spy(list);
```

Example:
```java
@Test
void whenSpyingOnList_thenCorrect() {
    List<String> list = new ArrayList<String>();
    List<String> spyList = spy(list);

    spyList.add("one");
    spyList.add("two");

    verify(spyList).add("one");
    verify(spyList).add("two");

    assertThat(spyList).hasSize(2);
}
```
The actual method is called

### How spy and mock differentiate?

> A mock is bare-bones shell. You need to perform `when(...).thenXXX` to test it.

```java
List mockedList = mock(ArrayList.class);

mockedList.add("one");
verify(mockedList).add("one");

// the list still 0 elements
assertThat(mockedList).hasSize(0);
```

A spy, on the other hand, creates the real object:
```java
List spyList = Mockito.spy(new ArrayList());

spyList.add("one");
Mockito.verify(spyList).add("one");

// 1 element in the spy list
assertThat(spyList).hasSize(1);
```


## `reset(mock)` - reset the mock behavior

> Reset the mock behaviour of a mock object. Then you can add another behaviour.

The following reset the mock. Then the test failed.
```java
when(calcService.add(20.0,10.0)).thenReturn(30.0);
Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);

//reset the mock	  
reset(calcService);

// test fail
Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);   
```


## Behavior Driven Development API in Mockito

> Behavior Driven Development is a style of writing tests uses given, when and then format as test methods.

```java
import static org.mockito.BDDMockito.*;

@Test
public void testAdd(){

  //Given
  given(calcService.add(20.0,10.0)).willReturn(30.0);

  //when
  double result = calcService.add(20.0,10.0);

  //then
  Assert.assertEquals(result,30.0,0);   
}
```
