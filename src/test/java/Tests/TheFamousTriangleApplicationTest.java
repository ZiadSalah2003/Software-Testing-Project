package Tests;

import Pages.TheFamousTriangleApplicationPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TheFamousTriangleApplicationTest extends TestBase {
    TheFamousTriangleApplicationPage trianglePage;

    @BeforeMethod
    public void setUp() {
        driver.navigate().to("https://testpages.eviltester.com/styled/apps/triangle/triangle001.html");
        trianglePage = new TheFamousTriangleApplicationPage(driver);
    }

    @Test
    public void testEquilateralTriangle() {
        trianglePage.testTriangle("5", "5", "5");
        String result = trianglePage.getTriangleType();
        Assert.assertTrue(result.contains("Equilateral"), "Expected Equilateral Triangle");
    }

    @Test
    public void testIsoscelesTriangle() {
        trianglePage.testTriangle("5", "5", "3");
        String result = trianglePage.getTriangleType();
        Assert.assertTrue(result.contains("Isosceles"), "Expected Isosceles Triangle");
    }

    @Test
    public void testScaleneTriangle() {
        trianglePage.testTriangle("4", "5", "6");
        String result = trianglePage.getTriangleType();
        Assert.assertTrue(result.contains("Scalene"), "Expected Scalene Triangle");
    }

    @Test
    public void testInvalidTriangle() {
        trianglePage.testTriangle("1", "2", "10");
        String result = trianglePage.getTriangleType();
        Assert.assertTrue(result.toLowerCase().contains("not a triangle"), "Expected invalid triangle warning");
    }
}
