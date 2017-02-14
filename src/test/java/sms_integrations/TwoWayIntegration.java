package sms_integrations;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.RandomString;
import java.util.concurrent.TimeUnit;
import static constants.Const.*;
import static utils.Operation.*;

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
    public void testUserCanCreateTwoWaySandboxIntegration() throws InterruptedException {
        webDriver.navigate().to(baseUrl+integrations);
        createNewIntegrationDialog();

        findByCss("#portal-app > div.modal.integration-dialog.wizard-dialog.ng-scope.ng-isolate-scope.in > div > div > div > div.cl-wizzard--body > div > div > div.ngsb-container > ng-include > fieldset > fieldset.cl-page-section.mod-reset-pt > div:nth-child(1) > div > input")
                 .sendKeys("Integration " + new RandomString(6).nextString());
        WebElement bNext = findByCss("#portal-app > div.modal.integration-dialog.wizard-dialog.ng-scope.ng-isolate-scope.in > div > div > div > div.cl-wizzard--footer.hide-on-small > button:nth-child(2)");
        bNext.click();
        //features tab
        findByCss("#portal-app > div.modal.integration-dialog.wizard-dialog.ng-scope.ng-isolate-scope.in > div > div > div > div.cl-wizzard--body > div > div > div.ngsb-container > ng-include > fieldset > fieldset.cl-page-section.mod-reset-pt > div.cl-page-section--descr > div:nth-child(2) > label > span")
                .click();
        bNext.click();
        // => settings tab
        bNext.click();
        // => two-way settings tab
        bNext.click();
        // => phone numbers tab
        bNext.click();
        // => save integration tab
        bNext.click();
        Thread.sleep(2000);
        WebElement bFinish = findByCss("#portal-app > div.modal.integration-dialog.wizard-dialog.ng-scope.ng-isolate-scope.in > div > div > div > div.cl-wizzard--footer.hide-on-small > button:nth-child(3)");
        Assert.assertEquals("Finish", bFinish.getText());
        // => finish
        bFinish.click();

        Thread.sleep(500);
        Assert.assertEquals("You have successfully created your integration", webDriver.switchTo().activeElement().getText());

    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        webDriver.close();
    }

}
