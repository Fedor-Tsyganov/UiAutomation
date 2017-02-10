package constants;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;

/**
 * Created by 2012mba4gb128gb on 1/30/17.
 */
public class Const {

    public static WebDriver webDriver;
    public static final String chromeDriver = "webdriver.chrome.driver";
    public static final String pathToCD = "/Users/2012mba4gb128gb/Desktop/dev/chromedriver";
    public static final String baseUrl = "https://qa-portal.clickatelllabs.com/#";
    public static final String baseEmailNames [] = {"fedor.clickatell", "mc2testportal"};
    public static final String defaultPassword = "12345678";
    public static final String HEADER_JSON = "application/json";

    public static final User firstTimeUser = new User("fedor.clickatell+0023@gmail.com","12345678",
            "Account", "B");

    public static final User userWithDetails = new User("fedor.clickatell+555@gmail.com","12345678",
            "Sergio", "Marko");

    public static final String leftNavNames []
            = new String[]{"Dashboard","SMS integrations","2-Way numbers",
            "Reporting","Developer tools","Help","Collapse nav"};

    public static final String integrationCardTextFirstTime[]
            = new String[]{"Create your first SMS integration",
            "Follow our easy step by step wizard to create your first REST API integration " +
                    "and start sending messages from your website, service or application.",
            "SMS integrations",
            "Interested in SMPP"};

    public static final String testPhonesCardTextFirstTime[]
            = new String[]{"Register your free test phones",
            "You can register up to 3 free test phones on your Clickatell account.",
            "Register test phones"};

    public static final String developerInfoCardTextFirstTime[]
            = new String[]{"Before getting started",
            "Setting up your first SMS integration is super easy. If you need any help, check out our Developer Tools " +
                    "for API and technical documentation, code snippets, tutorials and more.",
            "Developer tools"};

    public static final String phoneNumbersInfoCardText[]
            = new String[]{"Phone number",
            "Manage Long numbers and short codes for two-way messaging.",
            "2-Way numbers"};

}
