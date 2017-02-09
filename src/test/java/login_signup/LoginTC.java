package login_signup;


import constants.MC2PlatformTest;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.TestResultWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static constants.Const.*;

public class LoginTC {

    private static final String login = "/login";
    private ArrayList <MC2PlatformTest> testResults = new ArrayList<>();

    @BeforeTest
    public void setSystem(){
        System.setProperty(chromeDriver, pathToCD);
    }

    @BeforeMethod
    public void setDriver(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //ToDo: write Test Case about (no pass, no email)
    @Test(priority=0, description = "link to TC")
    public void testCannotLoginWithNoPasswordAndNoEmail() throws InterruptedException {
        webDriver.get(baseUrl + login);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("");
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys("");
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Thread.sleep(100);
        List<String> productName = new ArrayList<>();
        List<WebElement> allProductsName =
                webDriver.findElements(By.xpath("/html/body/div/div/ui-view/div/div/form/div[1]"));

        for(WebElement w : allProductsName) {
                productName.add(w.getText());
        }

        allProductsName = webDriver.findElements(By.xpath("/html/body/div/div/ui-view/div/div/form/div[2]"));

        for(WebElement w : allProductsName) {
            productName.add(w.getText());
        }

        String t1 = productName.get(0);
        String t2 = productName.get(1);
        Assert.assertEquals("Required", t1);
        Assert.assertEquals("Required", t2);

        webDriver.close();
    }

    @Test(priority=1, description = "PORT-1300")
    public void testCannotLoginWithValidEmailAndNoPassword () throws InterruptedException {
        webDriver.get(baseUrl + login);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("fedor.clickatell+555@gmail.com");
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys("");
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Thread.sleep(100);
        List<String> productName = new ArrayList<>();
        List<WebElement> allProductsName = webDriver.findElements(By.xpath("/html/body/div/div/ui-view/div/div/form/div[2]"));

        for (WebElement w : allProductsName) {
            productName.add(w.getText());
        }
        String text = productName.get(0);
        Assert.assertEquals("Required", text);
        webDriver.close();
    }

    @Test(priority=2, description = "PORT-1301")
    public void testCannotLoginWithNoEmailAndValidPassword() throws InterruptedException {
        webDriver.get(baseUrl + login);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("");
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys("12345678");
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Thread.sleep(100);
        List<String> productName = new ArrayList<>();
        List<WebElement> allProductsName =
                webDriver.findElements(By.xpath("/html/body/div/div/ui-view/div/div/form/div[1]"));

        for(WebElement w : allProductsName) {
            productName.add(w.getText());
        }

        System.out.println(productName);
        String text = productName.get(0);
        Assert.assertEquals("Required", text);
        webDriver.close();
    }


    //ToDo: write TC that user cannot login with invalid email and password
    @Test(priority=3, description = "link to TC")
    public void testCannotLoginWithInvalidEmailAndInvalidPassword() throws InterruptedException {
        webDriver.get(baseUrl + login);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("asdasdAD@asdasdasd");
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys("asdasdasdsad");
        webDriver.findElement(By.xpath("//form/div/button")).click();
        Thread.sleep(500);
        String text = webDriver.switchTo().activeElement().getText();
        Assert.assertEquals("Username or password is invalid", text);
        webDriver.close();
    }

    //ToDo:  write TC that user cannot login with invalid email and valid password
    @Test(priority=4, description = "link to TC")
    public void testCannotLoginWithInvalidEmailAndValidPassword() throws InterruptedException {
        webDriver.get(baseUrl + login);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("asdasdAD@asdasdasd");
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys("12345678");
        webDriver.findElement(By.xpath("//form/div/button")).click();
        Thread.sleep(500);
        String text = webDriver.switchTo().activeElement().getText();
        Assert.assertEquals("Username or password is invalid", text);
        webDriver.close();
    }

    //ToDo:  write TC that user cannot login with valid login and invalid password
    @Test(priority=5, description = "link to TC")
    public void testLoginWithValidEmailAndInvalidPassword() throws InterruptedException {
        webDriver.get(baseUrl + login);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("fedor.clickatell+555@gmail.com");
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys("123");
        webDriver.findElement(By.xpath("//form/div/button")).click();
        Thread.sleep(500);
        String text = webDriver.switchTo().activeElement().getText();
        Assert.assertEquals("Username or password is invalid", text);
        webDriver.close();
    }

    @Test(priority=6, description = "PORT-1295")
    public void testLoginWithValidEmailAndValidPassword(){
        webDriver.get(baseUrl + login);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("fedor.clickatell+555@gmail.com");
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys("12345678");
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Assert.assertEquals(true, webDriver.getTitle().isEmpty());
        webDriver.close();
    }

    //ToDo: write TC that link "Sign up now" works
    @Test(priority=7, description = "link to TC")
    public void testUserCanClickOnSignupNowLink()throws InterruptedException {
        webDriver.get(baseUrl + login);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        webDriver.findElement(By.linkText("Sign up now")).click();
        Thread.sleep(100);
        Assert.assertEquals("https://qa-portal.clickatelllabs.com/#/signup", webDriver.getCurrentUrl());
        webDriver.close();
    }

    //ToDo: write TC that link "Forgot password?" works
    @Test(priority=8, description = "link to TC")
    public void testUserCanClickForgotPasswordLink ()throws InterruptedException {
        webDriver.get(baseUrl + login);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        webDriver.findElement(By.linkText("Forgot password?")).click();
        Thread.sleep(100);
        Assert.assertEquals("https://qa-portal.clickatelllabs.com/#/forgotPasswordEmail", webDriver.getCurrentUrl());
        webDriver.close();
    }

    @AfterMethod
    public void afterMethod(ITestResult result){
        ITestNGMethod method = result.getMethod();
        String methodName = method.getMethodName();
        String issueId = method.getConstructorOrMethod().getMethod().getAnnotation(Test.class).description();
        try {
            if(result.getStatus() == ITestResult.SUCCESS) {
                testResults.add(new MC2PlatformTest(methodName, issueId, "Pass"));
            }

            else if(result.getStatus() == ITestResult.FAILURE) {
                testResults.add(new MC2PlatformTest(methodName, issueId, "Fail"));
            }

            else if(result.getStatus() == ITestResult.SKIP ){
                testResults.add(new MC2PlatformTest(methodName, issueId, "Skipped"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void afterTest(){
        TestResultWriter.write(LoginTC.class.getSimpleName()+".csv", testResults);
    }

}
