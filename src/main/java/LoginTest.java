import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
public class LoginTest {
    public static void main(String[] args) throws InterruptedException {

        // Locators
        By loginLinkLocator = By.className("account-title");
        By cookiePopUpLocator =  By.xpath("//button[contains(@mode, 'primary')]");
        By loginUserNameInputLocator = By.xpath("//input[contains(@placeholder, 'Your e-mail')]");
        By loginPasswordInputLocator = By.xpath("//input[contains(@type, 'password')]");
        By loginButtonLocator = By.xpath("//button[contains(@type, 'submit')]");

        //Creating current direction
        String currentDir = System.getProperty("user.dir");
        System.out.println("Current dir using System:" + currentDir);
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
        driver.findElement(loginLinkLocator).click();
        driver.findElement(By.xpath("//li[@class='menu-item user-zone-email']")).click();
        //driver.switchTo().frame("sdk-iframe-login-box");
        driver.findElement(loginUserNameInputLocator).sendKeys("masud33bd@gmail.com");
        driver.findElement(loginPasswordInputLocator).sendKeys("62320702Asba..");
        driver.findElement(loginButtonLocator).click();
        //driver.switchTo().defaultContent();

        // get the actual value of the title
        String actualTitle = driver.getTitle();
        System.out.println("actual title is: " + actualTitle);
        String expectedTitle = "eSky.co.uk - Flights, Airline Tickets, Flight Search, Deals";

        //Compare the actual title of the page with the expected one and print
        //The result as "Passed" or "Failed"
        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        // sleeping the execution for 4000 milly sec
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //close chrome
        //driver.close();
    }
}