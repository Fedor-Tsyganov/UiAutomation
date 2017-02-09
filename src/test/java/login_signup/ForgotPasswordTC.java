package login_signup;

import constants.MC2PlatformTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.TestResultWriter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static constants.Const.*;

/**
 * Created by aroma on 1/31/2017.
 */
public class ForgotPasswordTC {

    private static final String forgotPassword = "/forgotPasswordEmail";
    private ArrayList<MC2PlatformTest> testResults = new ArrayList<>();

    @BeforeTest
    public void setSystem(){
        System.setProperty(chromeDriver, pathToCD);
    }

    @BeforeMethod
    public void setDriver(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(priority = 0, description = "link to TC")
    public void testForgotPasswordEmailPlaceholderPresent()  {

        webDriver.get(baseUrl + forgotPassword);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement element = webDriver.findElement(By.xpath("//form/div/input[@name='email']"));
        Assert.assertEquals("Email", element.getAttribute("placeholder")); //placeholder
        webDriver.close();
    }

    @Test(priority = 1, description = "link to TC")
    public void testUserCanRestorePassword() throws InterruptedException {
        webDriver.get(baseUrl + forgotPassword);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("fedor.clickatell+555@gmail.com");
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Thread.sleep(500);
        String alert = webDriver.switchTo().activeElement().getText();
        Assert.assertEquals("The reset password link has been successfully sent to your email.", alert);
        webDriver.close();
    }
    @Test(priority = 2, description = "link to TC")
    public void testNonExistentEmailAlert() throws InterruptedException {
        webDriver.get(baseUrl + forgotPassword);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("abc@abracadabra.com");
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Thread.sleep(500);
        String alert = webDriver.switchTo().activeElement().getText();
        Assert.assertEquals("User with email abc@abracadabra.com not found.", alert);
        webDriver.close();
    }

    @Test(priority = 3, description = "link to TC")
    public void testInvalidEmailFormatAlert(){
        webDriver.get(baseUrl + forgotPassword);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("abc@?gmail.com");
        webDriver.findElement(By.xpath("//form/div/button")).click();

        String text = webDriver.findElement(By.xpath("//form/div[1]")).getText();
        Assert.assertEquals("Invalid", text);
        webDriver.close();
    }

    @Test(priority = 4, description = "link to TC")
    public void testBlankEmailAlert(){
        webDriver.get(baseUrl + forgotPassword);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/button")).click();

        String text = webDriver.findElement(By.xpath("//form/div[1]")).getText();
        Assert.assertEquals("Required", text);
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
