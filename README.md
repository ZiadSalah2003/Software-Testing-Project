# Web Testing Project

This project is designed to test various functionalities of the test pages available at [TestPages](https://testpages.eviltester.com/styled/index.html) using Selenium WebDriver and TestNG.

## Table of Contents
- [Web Testing Project](#web-testing-project)
  - [Table of Contents](#table-of-contents)
  - [Overview](#overview)
  - [Project Structure](#project-structure)
  - [Features Tested](#features-tested)
  - [Test Architecture](#test-architecture)
  - [Running Tests](#running-tests)
    - [Using Maven](#using-maven)
    - [Using IDE](#using-ide)
    - [Running Specific Tests](#running-specific-tests)
  - [Dependencies](#dependencies)
  - [Test Reports](#test-reports)
  - [Contributing](#contributing)

## Overview

This automated testing framework demonstrates the Page Object Model (POM) design pattern to create maintainable and reusable test code. The project tests various web application features using Selenium WebDriver for browser automation and TestNG for test organization and reporting.

## Project Structure

- `src/test/java/Pages`: Contains page object classes for each web page being tested
  - Each page class encapsulates page elements and actions
  - Follows Page Object Model best practices
- `src/test/java/Tests`: Contains test classes that use the page objects to test functionality
  - Organized by feature/page being tested
  - Includes a comprehensive end-to-end test suite

## Features Tested

The project includes automated tests for the following features:

| Feature | Description | Test Class |
|---------|-------------|------------|
| Client Server Form Input Validation | Tests form validation and submission | ClientServerFormInputValidationTest |
| Calculator | Tests basic arithmetic operations | CalculatorTest |
| Button Based Calculator | Tests calculator using UI button clicks | ButtonCalculatorTest |
| JavaScript Countdown | Tests timer functionality | CountdownTest |
| Search | Tests search functionality and results | SearchTest |
| 7 Char Val Validation | Tests character validation | CharValidationTest |
| Simple Note Taker | Tests note creation and deletion | NoteTakerTest |
| Canvas Drawing | Tests drawing shapes on canvas | CanvasDrawingTest |
| Canvas Scribble | Tests freehand drawing on canvas | ScribbleTest |
| Triangle Application | Tests triangle type identification | TheFamousTriangleApplicationTest |
| End-to-End Testing | Comprehensive test suite | ComprehensiveTest |

## Test Architecture

- **Page Object Model**: Each web page has a corresponding Page class
- **Test Base**: Common setup and teardown functionality in TestBase class
- **Parallel Execution**: Tests can be executed in parallel for faster results
- **Cross-Browser Testing**: Framework supports multiple browsers

## Running Tests

Tests can be run using the TestNG XML file in several ways:

### Using Maven

```bash
mvn test
```

### Using IDE

Run the `testing.xml` file directly from your IDE (Eclipse, IntelliJ, etc.)

### Running Specific Tests

To run specific test classes:

```bash
mvn test -Dtest=ClassName
```

Example: `mvn test -Dtest=CalculatorTest`

## Dependencies

- **Selenium WebDriver**: Browser automation framework
- **TestNG**: Test execution and reporting framework
- **WebDriverManager**: Automated driver management
- **JavascriptExecutor**: Advanced browser interactions

## Test Reports

TestNG reports are generated after test execution in the `target/surefire-reports` directory.

## Contributing

1. Fork the repository
2. Create a feature branch
3. Add tests or enhance existing ones
4. Submit a pull request
