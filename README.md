# JUnit Calculator Project 

## Project Summary

This project requires implementing a `Calculator` class and an extended `CalculatorAdvanced` class in Java, accompanied by comprehensive unit testing using JUnit5.


## Testing Requirements

* **Unit Tests:** Write JUnit5 tests for all implemented methods and constructors.
* **Parameterized Tests:** Wherever possible, tests must be parameterized to cover multiple input scenarios efficiently.
* **Test Coverage:** Achieve 100% code coverage, ensuring every method and branch is tested.
* **Hamcrest Matchers:** Use Hamcrest for expressive and readable assertions.
* **Mutational Testing:** After running mutation testing, address any identified weaknesses or missing test cases.
* **Test Organization:** Place all test classes in a dedicated test folder within the project structure.
* **Exception Testing:** Thoroughly test all custom exceptions, including:

  * `DivisionByZeroException`
  * `NotSupportedOperationException`
  * `NumberNotInAreaException`
* **Edge Cases:** Include boundary and edge cases, such as:

  * Division by zero
  * Invalid operators or actions
  * Factorial inputs outside allowed range
  * Armstrong and perfect number validations for valid and invalid inputs


