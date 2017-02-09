package dashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static constants.Const.*;

/**
 * Created by 2012mba4gb128gb on 2/8/17.
 */
public class DashboardTC {

    public void login(){
        if (webDriver != null){
            webDriver.get(baseUrl+"/login");
            webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys(accountFirstTime[0]);
            webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys(accountFirstTime[1]);
            webDriver.findElement(By.xpath("//form/div/button")).click();
        }
    }

    @BeforeTest
    public void setSystem(){
        System.setProperty(chromeDriver, pathToCD);
    }

    @BeforeMethod
    public void setDriver(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        login();
    }


    //ToDo: TC to verify that everything is displayed correctly
    @Test
    public void testAllUiElementsPresentWhenEnterFirstTime(){
        webDriver.navigate().to(baseUrl);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement element = webDriver.findElement(By.xpath("/html/body/div/div/ui-view/section/div"));

        Assert.assertEquals("Welcome, "+ accountFirstTime[2], element.getText());

        //Integration Card
        Assert.assertEquals(integrationCardText[0],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(1) > div > h3"))
                        .getText());
        Assert.assertEquals(integrationCardText[1],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(1) > div > ng-include > div.dashboard-step-content.ng-scope"))
                        .getText());
        Assert.assertEquals(integrationCardText[2],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(1) > div > ng-include > div.btn-group.ng-scope > button.button.button-primary"))
                        .getText());
        Assert.assertEquals(integrationCardText[3],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(1) > div > ng-include > div.btn-group.ng-scope > button.button.button-secondary"))
                        .getText());

        //Register Test Phone Card
        Assert.assertEquals(testPhonesCardText[0],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(2) > div > h3"))
                        .getText());
        Assert.assertEquals(testPhonesCardText[1],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(2) > div > ng-include > div.dashboard-step-content.ng-binding.ng-scope"))
                        .getText());
        Assert.assertEquals(testPhonesCardText[2],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(2) > div > ng-include > div.btn-group.ng-scope > button"))
                        .getText());

        //Getting Started Card
        Assert.assertEquals(developerInfoCardText[0],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(3) > div > h3"))
                        .getText());
        Assert.assertEquals(developerInfoCardText[1],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(3) > div > ng-include > div.dashboard-step-content.ng-scope"))
                        .getText());
        Assert.assertEquals(developerInfoCardText[2],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(3) > div > ng-include > div.btn-group.ng-scope > button"))
                        .getText());

        //Balance is 0.00 EUR
        Assert.assertEquals("0.00 EUR",
                webDriver.findElement(By.cssSelector("#portal-app > div > header > div > div.balance-block > div > div.balance-total.ng-binding"))
                        .getText());
        webDriver.close();
    }


    /*
    @Test
    public void testAllUiElementsPresentWhenEnterWithTestPhone(){

    }
    */

}
