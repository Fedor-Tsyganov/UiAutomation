package sms_integrations;

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
import static utils.Operation.createNewIntegrationDialog;
import static utils.Operation.login;

/**
 * Created by 2012mba4gb128gb on 1/30/17.
 */
public class TwoWayIntegration {

    private int count = 0;
    private static final String integrations = "/integrations/sms";


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

    @Test(priority = 1, description = "link to TC")
    public void testUserCanCreateTwoWaySandboxIntegration(){
        Assert.assertEquals("Create new integration", createNewIntegrationDialog().getText());

        webDriver.close();
    }

}
