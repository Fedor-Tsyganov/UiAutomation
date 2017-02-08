package constants;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by 2012mba4gb128gb on 1/30/17.
 */
public class Const {

    public static WebDriver webDriver; //= new ChromeDriver();
    public static final String chromeDriver = "webdriver.chrome.driver";
    public static final String pathToCD = "/Users/2012mba4gb128gb/Desktop/dev/chromedriver";
    public static final String baseUrl = "https://qa-portal.clickatelllabs.com/#";
    public static final String baseEmailNames [] = {"fedor.clickatell", "mc2testportal"};
    public static final String defaultPassword = "12345678";
}
