package dashboard;

import constants.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static constants.Const.*;
import static utils.Operation.login;

/**
 * Created by 2012mba4gb128gb on 2/8/17.
 */
public class DashboardTC {

    private int count = 0;

    @BeforeTest
    public void setSystem(){
        System.setProperty(chromeDriver, pathToCD);
    }

    @BeforeMethod
    public void setDriver(){
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        if (count < 1) {
            login(firstTimeUser);
            count++;
        } else {
            login(userWithDetails);
        }
    }


    //ToDo: TC to verify that everything is displayed correctly
    @Test (priority = 0, description = "link to TC")
    public void testAllUiElementsPresentWhenEnterFirstTime(){
        webDriver.navigate().to(baseUrl);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement element = webDriver.findElement(By.xpath("/html/body/div/div/ui-view/section/div"));

        Assert.assertEquals("Welcome, "+ firstTimeUser.getFirstName(), element.getText());

        //Integration Card
        Assert.assertEquals(integrationCardTextFirstTime[0],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(1) > div > h3"))
                        .getText());
        Assert.assertEquals(integrationCardTextFirstTime[1],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(1) > div > ng-include > div.dashboard-step-content.ng-scope"))
                        .getText());
        Assert.assertEquals(integrationCardTextFirstTime[2],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(1) > div > ng-include > div.btn-group.ng-scope > button.button.button-primary"))
                        .getText());
        Assert.assertEquals(integrationCardTextFirstTime[3],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(1) > div > ng-include > div.btn-group.ng-scope > button.button.button-secondary"))
                        .getText());

        //Register Test Phone Card
        Assert.assertEquals(testPhonesCardTextFirstTime[0],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(2) > div > h3"))
                        .getText());
        Assert.assertEquals(testPhonesCardTextFirstTime[1],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(2) > div > ng-include > div.dashboard-step-content.ng-binding.ng-scope"))
                        .getText());
        Assert.assertEquals(testPhonesCardTextFirstTime[2],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(2) > div > ng-include > div.btn-group.ng-scope > button"))
                        .getText());

        //Getting Started Card
        Assert.assertEquals(developerInfoCardTextFirstTime[0],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(3) > div > h3"))
                        .getText());
        Assert.assertEquals(developerInfoCardTextFirstTime[1],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(3) > div > ng-include > div.dashboard-step-content.ng-scope"))
                        .getText());
        Assert.assertEquals(developerInfoCardTextFirstTime[2],
                webDriver.findElement(By.cssSelector("#portal-app > div > div > ui-view > div > div:nth-child(3) > div > ng-include > div.btn-group.ng-scope > button"))
                        .getText());

        //Balance is 0.00 EUR
        Assert.assertEquals("0.00 EUR",
                webDriver.findElement(By.cssSelector("#portal-app > div > header > div > div.balance-block > div > div.balance-total.ng-binding"))
                        .getText());
        webDriver.close();
    }

    @Test(priority = 1, description = "link to TC")
    public void testAllUiElementsPresentWhenEnterWithTestPhone(){
        webDriver.navigate().to(baseUrl);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement element = webDriver.findElement(By.xpath("/html/body/div/div/ui-view/section/div"));

        Assert.assertEquals("Welcome, "+ userWithDetails.getFirstName(), element.getText());
        Assert.assertEquals("8.08 USD",
                webDriver.findElement(By.cssSelector("#portal-app > div > header > div > div.balance-block > div > div.balance-total.ng-binding"))
                        .getText());
        webDriver.close();
    }


    @Test(priority = 2, description = "link to TC")
    public void testRegisterPhoneNumber(){
        webDriver.navigate().to(baseUrl);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.close();
    }

}
