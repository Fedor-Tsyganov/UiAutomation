package login_signup;

import constants.MC2PlatformTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.RandomString;
import utils.TestResultWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
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
    private ArrayList <MC2PlatformTest> testResults = new ArrayList<>();
    private String BLANK = "";

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
    public void testSignupAllFieldsArePresent(){

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

    @Test(priority = 1, description = "link to TC")
    public void testUserCanSignupWithValidCredentials() throws InterruptedException {
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


    @Test(priority = 2, description = "link to TC")
    public void testUserCannotSignupWithOnlyFirstName() throws InterruptedException{

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
    @Test(priority = 3, description = "link to TC")
    public void testUserCannotSignupWithAllBlankFields(){

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

    @Test(priority = 4, description = "link to TC")
    public void testUserCannotSignupWithBlankName(){

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

    @Test(priority = 5, description = "link to TC")
    public void testCannotSignupWithBlankAccountName() {

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

    @Test(priority = 6, description = "link to TC")
    public void testCannotSignupWithBlankEmail() {

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

    @Test(priority = 7, description = "link to TC")
    public void testCannotSignupWithBlankPassword() {

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

    @Test(priority = 8, description = "link to TC")
    public void testCannotSignupWithShortPassword() throws InterruptedException {

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
