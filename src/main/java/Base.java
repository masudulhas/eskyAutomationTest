import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class Base  {

    public static ChromeDriver getDriver(){

        //Locators
        By cookiePopUpLocator =  By.xpath("//button[contains(@mode, 'primary')]");

        //Creating current direction
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current Direction Using System: " + currentDir);
        System.setProperty("webdriver.chrome.driver", currentDir + "\\src\\main\\resources\\driver\\chromedriver.exe");

        //Notification disable
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");

        //Creating driver object
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize(); //Maximize window
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

       //setting up url
        String baseUrl = "https://www.esky.co.uk";

        // launch chrome and execute the test steps
        driver.get(baseUrl);
        driver.findElement(cookiePopUpLocator).click();
        return  driver;
    }
}
