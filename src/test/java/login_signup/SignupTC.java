package login_signup;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.RandomString;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static constants.Const.*;
import static utils.RandomEmail.getRandomEmail;

/**
 * Created by Fedor Tsyganov on 1/31/17.
 */
public class SignupTC {

    private static final String signup = "/signup";
    private String [] names = new String[]{"text", "accountName", "email", "password"};
    private String [] placeholderValues = new String[]{"First and Last Name", "Account Name", "Email", "Password"};
    private RandomString randomString;
    private String BLANK = "";

    @Before
    public void setUp(){
        System.setProperty(chromeDriver, pathToCD);
        webDriver = new ChromeDriver();
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

    //Happy Pass. TC: user can sign-up with valid credentials
    //
    @Test
    public void testSignup_1() throws InterruptedException {
        webDriver.get(baseUrl + signup);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        randomString = new RandomString(5);
        String f_name = "Auto_"+randomString.nextString();
        String l_name = "Test_"+randomString.nextString();
        String acc_name = "auto_"+randomString.nextString();
        String email = getRandomEmail();

        webDriver.findElement(By.xpath("//form/div/input[@name='text']")).sendKeys(f_name+" "+l_name);
        webDriver.findElement(By.xpath("//form/div/input[@name='accountName']")).sendKeys(acc_name);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys(email);
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys(defaultPassword);
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Thread.sleep(600);

        Assert.assertEquals("https://qa-portal.clickatelllabs.com/#/login", webDriver.getCurrentUrl()); //user redirected to valid url
        Assert.assertEquals("",webDriver.findElement(By.xpath("//form/div/input[@name='email']")).getText()); //email is blank
        Assert.assertEquals("",webDriver.findElement(By.xpath("//form/div/input[@name='password']")).getText()); //password is blank
        Assert.assertEquals("A confirmation email has been sent to "+email+ " to complete your sign up process",
                webDriver.findElement(By.xpath("/html/body/div/div/ui-view/div/div/form/div[4]")).getText()); //message is displayed

        //ToDo: chech that sign-up link is not visible
        webDriver.close();
    }


    //TC: user cannot sign-up with only first name
    //
    @Test
    public void testSignup_2() throws InterruptedException{

        webDriver.get(baseUrl + signup);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        randomString = new RandomString(5);
        String f_name = "Auto_"+randomString.nextString();
        String acc_name = "auto_"+randomString.nextString();
        String email = getRandomEmail();

        webDriver.findElement(By.xpath("//form/div/input[@name='text']")).sendKeys(f_name);
        webDriver.findElement(By.xpath("//form/div/input[@name='accountName']")).sendKeys(acc_name);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys(email);
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys(defaultPassword);
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Thread.sleep(600);

        String text = webDriver.switchTo().activeElement().getText();
        Assert.assertEquals("lastName: may not be empty", text);

        webDriver.close();
    }

    //TC: user cannot sign-up with all blank fields
    //
    @Test
    public void testSignup_3(){

        webDriver.get(baseUrl + signup);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.findElement(By.xpath("//form/div/input[@name='text']")).sendKeys(BLANK);
        webDriver.findElement(By.xpath("//form/div/input[@name='accountName']")).sendKeys(BLANK);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys(BLANK);
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys(BLANK);
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Assert.assertEquals(true, (webDriver
                .findElement(By.xpath("//form/div/input[@name='text']")))
                .getAttribute("class")
                .contains("ng-invalid"));

        Assert.assertEquals(true, (webDriver
                .findElement(By.xpath("//form/div/input[@name='accountName']")))
                .getAttribute("class")
                .contains("ng-invalid"));

        Assert.assertEquals(true, (webDriver
                .findElement(By.xpath("//form/div/input[@name='email']")))
                .getAttribute("class")
                .contains("ng-invalid"));

        Assert.assertEquals(true, (webDriver
                .findElement(By.xpath("//form/div/input[@name='password']")))
                .getAttribute("class")
                .contains("ng-invalid"));

        webDriver.close();
    }

    //TC: user cannot sign-up with blank name
    //
    @Test
    public void testSignup_4(){

        webDriver.get(baseUrl + signup);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        randomString = new RandomString(5);
        String acc_name = "auto_"+randomString.nextString();
        String email = getRandomEmail();

        webDriver.findElement(By.xpath("//form/div/input[@name='text']")).sendKeys(BLANK);
        webDriver.findElement(By.xpath("//form/div/input[@name='accountName']")).sendKeys(acc_name);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys(email);
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys(defaultPassword);
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Assert.assertEquals(true, (webDriver
                .findElement(By.xpath("//form/div/input[@name='text']")))
                .getAttribute("class")
                .contains("ng-invalid"));

        webDriver.close();
    }

    //TC: user cannot sign-up with blank account name
    //
    @Test
    public void testSignup_5() {

        webDriver.get(baseUrl + signup);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        randomString = new RandomString(5);
        String f_name = "Auto_"+randomString.nextString();
        String l_name = "Test_"+randomString.nextString();
        String email = getRandomEmail();

        webDriver.findElement(By.xpath("//form/div/input[@name='text']")).sendKeys(f_name+" "+l_name);
        webDriver.findElement(By.xpath("//form/div/input[@name='accountName']")).sendKeys(BLANK);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys(email);
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys(defaultPassword);
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Assert.assertEquals(true, (webDriver
                .findElement(By.xpath("//form/div/input[@name='accountName']")))
                .getAttribute("class")
                .contains("ng-invalid"));

        webDriver.close();
    }

    //TC: user cannot sign-up with blank email
    //
    @Test
    public void testSignup_6() {

        webDriver.get(baseUrl + signup);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        randomString = new RandomString(5);
        String f_name = "Auto_"+randomString.nextString();
        String l_name = "Test_"+randomString.nextString();
        String acc_name = "auto_"+randomString.nextString();

        webDriver.findElement(By.xpath("//form/div/input[@name='text']")).sendKeys(f_name+" "+l_name);
        webDriver.findElement(By.xpath("//form/div/input[@name='accountName']")).sendKeys(acc_name);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys(BLANK);
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys(defaultPassword);
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Assert.assertEquals(true, (webDriver
                .findElement(By.xpath("//form/div/input[@name='email']")))
                .getAttribute("class")
                .contains("ng-invalid"));

        webDriver.close();
    }

    //TC: user cannot sign-up with blank password
    //
    @Test
    public void testSignup_7() {

        webDriver.get(baseUrl + signup);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        randomString = new RandomString(5);
        String f_name = "Auto_"+randomString.nextString();
        String l_name = "Test_"+randomString.nextString();
        String acc_name = "auto_"+randomString.nextString();
        String email = getRandomEmail();

        webDriver.findElement(By.xpath("//form/div/input[@name='text']")).sendKeys(f_name+" "+l_name);
        webDriver.findElement(By.xpath("//form/div/input[@name='accountName']")).sendKeys(acc_name);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys(email);
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys(BLANK);
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Assert.assertEquals(true, (webDriver
                .findElement(By.xpath("//form/div/input[@name='password']")))
                .getAttribute("class")
                .contains("ng-invalid"));

        webDriver.close();
    }

    //TC: user cannot sign-up with short password (min 8 and max 128 characters)
    //
    @Test
    public void testSignup_8() throws InterruptedException {

        webDriver.get(baseUrl + signup);
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        randomString = new RandomString(5);
        String f_name = "Auto_"+randomString.nextString();
        String l_name = "Test_"+randomString.nextString();
        String acc_name = "auto_"+randomString.nextString();
        String email = getRandomEmail();

        webDriver.findElement(By.xpath("//form/div/input[@name='text']")).sendKeys(f_name+" "+l_name);
        webDriver.findElement(By.xpath("//form/div/input[@name='accountName']")).sendKeys(acc_name);
        webDriver.findElement(By.xpath("//form/div/input[@name='email']")).sendKeys(email);
        webDriver.findElement(By.xpath("//form/div/input[@name='password']")).sendKeys("123");
        webDriver.findElement(By.xpath("//form/div/button")).click();

        Thread.sleep(600);

        String text = webDriver.switchTo().activeElement().getText();
        Assert.assertEquals("password: size must be between 8 and 128", text);

        webDriver.close();
    }
}
