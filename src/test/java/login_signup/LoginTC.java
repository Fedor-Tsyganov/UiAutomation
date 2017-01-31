package login_signup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static constants.Const.*;


public class LoginTC {

    private static final String login = "/login";

    @Before
    public void setUp(){
        System.setProperty(chromeDriver, pathToCD);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    //Invalid: no pass, no email
    //ToDo: write Test Case about (no pass, no email)
    @Test
    public void testLogin_0() throws InterruptedException {
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

    //Invalid: no pass, any email
    //PORT-1300
    @Test
    public void testLogin_1 () throws InterruptedException {
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
        //System.out.println(productName);
        String text = productName.get(0);
        Assert.assertEquals("Required", text);
        webDriver.close();
    }

    //Invalid: no email, pass
    //PORT-1301
    @Test
    public void testLogin_2() throws InterruptedException {
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


    //invalid email and invalid password
    //ToDo: write TC that user cannot login with invalid email and password
    @Test
    public void testLogin_3() throws InterruptedException {
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

    //Invalid: invalid email, valid pass
    //ToDo:  write TC that user cannot login with invalid email and valid password
    @Test
    public void testLogin_4() throws InterruptedException {
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

    //Invalid: valid email, invalid pass
    //ToDo:  write TC that user cannot login with valid login and invalid password
    @Test
    public void testLogin_5() throws InterruptedException {
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

    //valid email and password
    //PORT-1295
    @Test
    public void testLogin_6(){
        webDriver.get(baseUrl + login);
        webDriver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys("fedor.clickatell+555@gmail.com");
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys("12345678");
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Assert.assertEquals(true, webDriver.getTitle().isEmpty());
        webDriver.close();
    }


}
