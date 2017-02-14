package utils;

import constants.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import static constants.Const.baseUrl;
import static constants.Const.webDriver;

public class Operation {

    public static void login(User user){
        if (webDriver != null){
            webDriver.get(baseUrl+"/login");
            webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys(user.getEmail());
            webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys(user.getPassword());
            webDriver.findElement(By.xpath("//form/div/button")).click();
        }
    }

    public static WebElement createNewIntegrationDialog(){
        webDriver.navigate().to(baseUrl+"/integrations/sms");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement createNewIntegrationButton = webDriver
                .findElement(
                        By.cssSelector("#portal-app > div.main > div > ui-view > section > div.cl-header--controls.cl-header--item > div > button")
                );

        createNewIntegrationButton.click();
        return createNewIntegrationButton;
    }

    public static void closeNewIntegrationDialog(){
        findByCss("#c4cc9515-a905-4639-9864-317b46fd9c30 > rect:nth-child(4)").click();
    }

    public static WebElement findByCss(String selector){
        return webDriver.findElement(By.cssSelector(selector));
    }

}
