</> Markdown
# Library Practice project
## Description:
The Library Practice Project started as a Java OOP practice project focused on building a simple library system.

After learning Selenium test automation and implementing it in the SauceDemo automation project, I returned to 
this project to improve its structure, add new functionality, and implement my first JUnit tests.

The goal of this phase was to practice unit testing, refactor existing logic based on test results, 
and gain a better understanding of test-driven development principles.
## Tech Stack:

    - Java (OpenJDK 26)
    - IntelliJ IDEA 2026.1
    - Object-Oriented-Programming
    - JUnit 5
    - Git & GitHub

## Features

- Add books to the library
- Register users
- Borrow and return books
- Track active and archived loans
- Validate business rules using exceptions
- Retrieve youngest user
- Sort users by surname
- 
## How to run

1. Open the project in IntelliJ IDEA
2. Load Maven dependencies (IntelliJ will handle this automatically)
3. Navigate to `src/test/java/biblioteka/BibliotekaTest.java`
4. Run tests using JUnit from the IDE

OR via terminal if Maven is installed:

```bash
mvn test
```
# Testing

## This project includes:

- Unit tests for core business logic
- Positive test scenarios
- Negative test scenarios
- Exception handling tests
- Sorting and data validation tests
- Regression testing after refactoring
- 
## What I learned

- Writing unit tests with JUnit 5
- Structuring test data using helper methods
- Refactoring code based on test feedback
- Handling exceptions in business logic
- Using Git for version control and project history
- Understanding regression testing in practice

## Future Improvements

- Integration with SQL database instead of in-memory storage 
- Better handling of date/time logic for testing consistency
- Implementation of equals() and hashCode()
- Expansion of business rules and validation
- Introduction of API layer for external access



