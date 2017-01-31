package login_signup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static constants.Const.*;

/**
 * Created by Fedor Tsyganov on 1/31/17.
 */
public class SignupTC {

    private static final String signup = "/signup";
    private String [] names = new String[]{"text", "accountName", "email", "password"};
    private String [] placeholderValues = new String[]{"First and Last Name", "Account Name", "Email", "Password"};

    @Before
    public void setUp(){
        System.setProperty(chromeDriver, pathToCD);
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    //TC all fields are present with valid placeholders
    //
    @Test
    public void testSignup_0(){

        webDriver.get(baseUrl + signup);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        for (int i = 0; i < 4; i++){
            List<WebElement> allFieldsNames
                    = webDriver.findElements(By.xpath("/html/body/div/div/ui-view/div/div/form/div/input[@name='"+names[i]+"']"));
            for (WebElement w : allFieldsNames) {
                Assert.assertEquals(true, w.getAttribute("placeholder").equals(placeholderValues[i])); //placeholder
                Assert.assertEquals(true, w.getText().equals("")); //empty textfield
            }
        }

        webDriver.close();
    }

    //Happy Pass. TC: user can signup with valid credeintials
    //
    @Test
    public void testSignup_1(){

        webDriver.get(baseUrl + signup);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        webDriver.close();
    }

}
