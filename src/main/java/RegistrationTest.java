import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class RegistrationTest {
    public static void main(String[] args) {

        //Locators
        By loginLinkLocator = By.className("account-title");
        By cookiePopUpLocator =  By.className("css-47sehv");
        By registerLinkLocator = By.className("register-button");
        By emailRegisterLocator = By.xpath("//input[contains(@name, 'email')]");
        By createAccountButtonLocator = By.xpath("//button[contains(@type, 'submit')]");

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

        //Setting up url
        String baseUrl = "https://www.esky.co.uk";

        //Launch chrome and execute the test steps
        driver.get(baseUrl);
        driver.findElement(cookiePopUpLocator).click();
        driver.findElement(loginLinkLocator).click();

        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        //Check we don't have other windows open already
        assert driver.getWindowHandles().size() == 1;

        //Click the link which opens in a new window
        driver.switchTo().frame("sdk-iframe-login-box");
        driver.findElement(registerLinkLocator).click();
        driver.switchTo().defaultContent();

        //Loop through until we find a new window handle
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        driver.findElement(emailRegisterLocator).sendKeys("masudulha@gmail.com");
        driver.findElement(By.className("checkbox")).click();
        driver.findElement(By.name("newsletterAcceptance")).click();
        driver.findElement(createAccountButtonLocator).click();

        //Get the actual value of the title
        String actualTitle = driver.getTitle();
        System.out.println("actual title is: " + actualTitle);
        String expectedTitle = "eSky.co.uk";

        //Compare the actual title of the page with the expected one and print
        //The result as "Passed" or "Failed"
        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }
        //Sleeping the execution for 4000 millisecond
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Close chrome
        driver.close();
    }
}
