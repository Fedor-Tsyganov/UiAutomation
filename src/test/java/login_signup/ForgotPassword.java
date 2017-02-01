package login_signup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static constants.Const.*;

/**
 * Created by aroma on 1/31/2017.
 */
public class ForgotPassword {

    private static final String forgotPassword = "/forgotPasswordEmail";

    @Before
    public void setUp() {
        System.setProperty(chromeDriver, pathToCD);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }

    @Test
    public void forgotPassword_0()  {

        webDriver.get(baseUrl + forgotPassword);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        WebElement element = webDriver.findElement(By.xpath("//form/div/input[@name='email']"));
        Assert.assertEquals("Email", element.getAttribute("placeholder")); //placeholder
        webDriver.close();
    }

    @Test
    public void forgotPassword_1() throws InterruptedException {

        webDriver.get(baseUrl + forgotPassword);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("fedor.clickatell+555@gmail.com");
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Thread.sleep(250);
        String alert = webDriver.switchTo().activeElement().getText();
        Assert.assertEquals("The reset password link has been successfully sent to your email.", alert);
        webDriver.close();
    }
    @Test
    public void forgotPassword_2()throws InterruptedException {

        webDriver.get(baseUrl + forgotPassword);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("abc@abracadabra.com");
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Thread.sleep(250);
        String alert = webDriver.switchTo().activeElement().getText();
        Assert.assertEquals("User with email abc@abracadabra.com not found.", alert);
        webDriver.close();
    }

    @Test
    public void forgotPassword_3(){

        webDriver.get(baseUrl + forgotPassword);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("abc@?gmail.com");
        webDriver.findElement(By.xpath("//form/div/button")).click();

       // Thread.sleep(500);
        String text = webDriver.findElement(By.xpath("//form/div[1]")).getText();
        Assert.assertEquals("Invalid", text);
        webDriver.close();
    }

    @Test
    public void forgotPassword_4(){

        webDriver.get(baseUrl + forgotPassword);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/button")).click();

       // Thread.sleep(500);
        String text = webDriver.findElement(By.xpath("//form/div[1]")).getText();
        Assert.assertEquals("Required", text);
        webDriver.close();
    }
}
