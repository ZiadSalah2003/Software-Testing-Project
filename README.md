# Web Testing Project

This project is designed to test various functionalities of the test pages available at [TestPages](https://testpages.eviltester.com/styled/index.html).

## Project Structure

- `src/test/java/Pages`: Contains page object classes for each web page being tested
- `src/test/java/Tests`: Contains test classes that use the page objects to test functionality

## Features Tested

1. Client Server Form Input Validation
2. Calculator
3. Button Based Calculator
4. JavaScript Countdown Test Page
5. Search
6. 7 Char Val Validation
7. Simple Note Taker
8. Learn More and More Practice Sites links

## Running Tests

Tests can be run using the TestNG XML file:

```
mvn test
```

Or directly from your IDE by running the `testing.xml` file.

## Dependencies

- Selenium WebDriver
- TestNG
- WebDriverManager
