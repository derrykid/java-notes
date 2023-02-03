[toc]

## Samples

[JUnit GitHub samples](https://github.com/junit-team/junit5-samples) 

### Minimum example

```java
import static org.junit.jupiter.api.Assertions.assertEquals;

import example.util.Calculator;

import org.junit.jupiter.api.Test;

class MyFirstJUnitJupiterTests {

    private final Calculator calculator = new Calculator();

    @Test
    void addition() {
        assertEquals(2, calculator.add(1, 1));
    }

}
```

## dependency

### `junit-bom` - JUnit's Bill Of Materials (BOM)

> When including this BOM, it will ensure to align and manage all JUnit 5 dependency versions for you.

```groove
// with the BOM, no version needed
testImplementation('org.junit.jupiter:junit-jupiter')

// when using no BOM, version info is needed
testImplementation('org.junit.jupiter:junit-jupiter:5.7.1')
```

### `useJUnitPlatform()` in Gradle build script

> The `useJUnitPlatform()` instructs the Gradle test task to use the JUnit Platform for executing your tests. That's required.

## JUnit Annotations

Most core annotations are located in the `org.junit.jupiter.api` package in the `junit-jupiter-api` module.

| Annotation         | Description                               |
|--------------------|-------------------------------------------|
| @Test              | Denotes the method is a test              |
| @ParameterizedTest | Denotes the method is parameterized test  |
| @DisplayName       | custom display name for the class/method  |
| @BeforeEach        | Before any test run, run this             |
| @AfterEach         | After each test run, run this             |
| @BeforeAll         | Execute before all test run               |
| @AfterAll          | Execute after all test                    |
| @Nested            | The nested test class is non-static class |
| @Tag               | Used to declare tags for filtering tests  |
| @Disable           | Disable the method/class                  |

## Writing Tests

Test classes/methods aren't required to be `public`, but must not be `private`. It's generally recommended to omit the `public`.

Example:
```java
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StandardTests {

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void init() {
    }

    @Test
    void succeedingTest() {
    }

    @Test
    void failingTest() {
        fail("a failing test");
    }

    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    @Test
    void abortedTest() {
        assumeTrue("abc".contains("Z"));
        fail("test should have been aborted");
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

}
```

### Pattern - writing test in a typical way

```java
// GIVEN a person
...
// WHEN setting a negative age
...
// THEN it throws bc not allowed
...
```

### Assertions - test the functionality

> All JUnit Jupiter assertions are `static` methods in the `org.junit.jupiter.api.Assertions` class.

The following sample from [user guide](https://junit.org/junit5/docs/current/user-guide/#writing-tests-assertions) is covered most use cases.

```java
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofMinutes;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CountDownLatch;

import example.domain.Person;
import example.util.Calculator;

import org.junit.jupiter.api.Test;

class AssertionsDemo {

    private final Calculator calculator = new Calculator();

    private final Person person = new Person("Jane", "Doe");

    @Test
    void standardAssertions() {
        assertEquals(2, calculator.add(1, 1));
        assertEquals(4, calculator.multiply(2, 2),
                "The optional failure message is now the last parameter");
        assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- "
                + "to avoid constructing complex messages unnecessarily.");
    }

    @Test
    void groupedAssertions() {
        // In a grouped assertion all assertions are executed, and all
        // failures will be reported together.
        assertAll("person",
            () -> assertEquals("Jane", person.getFirstName()),
            () -> assertEquals("Doe", person.getLastName())
        );
    }

    @Test
    void dependentAssertions() {
        // Within a code block, if an assertion fails the
        // subsequent code in the same block will be skipped.
        assertAll("properties",
            () -> {
                String firstName = person.getFirstName();
                assertNotNull(firstName);

                // Executed only if the previous assertion is valid.
                assertAll("first name",
                    () -> assertTrue(firstName.startsWith("J")),
                    () -> assertTrue(firstName.endsWith("e"))
                );
            },
            () -> {
                // Grouped assertion, so processed independently
                // of results of first name assertions.
                String lastName = person.getLastName();
                assertNotNull(lastName);

                // Executed only if the previous assertion is valid.
                assertAll("last name",
                    () -> assertTrue(lastName.startsWith("D")),
                    () -> assertTrue(lastName.endsWith("e"))
                );
            }
        );
    }

    @Test
    void exceptionTesting() {
        Exception exception = assertThrows(ArithmeticException.class, () ->
            calculator.divide(1, 0));
        assertEquals("/ by zero", exception.getMessage());
    }

    @Test
    void timeoutNotExceeded() {
        // The following assertion succeeds.
        assertTimeout(ofMinutes(2), () -> {
            // Perform task that takes less than 2 minutes.
        });
    }

    @Test
    void timeoutNotExceededWithResult() {
        // The following assertion succeeds, and returns the supplied object.
        String actualResult = assertTimeout(ofMinutes(2), () -> {
            return "a result";
        });
        assertEquals("a result", actualResult);
    }

    @Test
    void timeoutNotExceededWithMethod() {
        // The following assertion invokes a method reference and returns an object.
        String actualGreeting = assertTimeout(ofMinutes(2), AssertionsDemo::greeting);
        assertEquals("Hello, World!", actualGreeting);
    }

    @Test
    void timeoutExceeded() {
        // The following assertion fails with an error message similar to:
        // execution exceeded timeout of 10 ms by 91 ms
        assertTimeout(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            Thread.sleep(100);
        });
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            new CountDownLatch(1).await();
        });
    }

    private static String greeting() {
        return "Hello, World!";
    }

}
```

### `AssertJ`, `Hamcrest`, etc - Third-party Assertion Libraries

There are some third-party assertion libraries that provide additional functionality for testing. Such libraries are for example: `AssertJ`, `Hamcrest`, `Truth`, etc.

### Assumption - used to disable tests

> When assertion fails, it's failed test. However, when assumption fails, it moves to the next test.

In short Assume used to disable tests. For example, the following disables a test on Linux: 

```java
Assume.assumeFalse(System.getProperty("os.name").contains("Linux"));
```

## AssertJ

[Samples repository] (https://github.com/derrykid/gradle-junit-test-sample)

Combine the `AssertJ` with `JUnit`.
