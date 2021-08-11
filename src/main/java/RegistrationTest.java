import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class RegistrationTest {
    public static void main(String[] args) {

        ChromeDriver driver = Base.getDriver();
        //Locators
        By loginLinkLocator = By.className("account-title");
        By registerLinkLocator = By.xpath("//button[@class='button']");
        By emailRegisterLocator = By.xpath("//input[contains(@name, 'email')]");
        By createAccountButtonLocator = By.xpath("//button[contains(@type, 'submit')]");

        // launch chrome and execute the test steps
        driver.findElement(loginLinkLocator).click();
        driver.findElement(By.xpath("//li[@class='menu-item user-zone-email']")).click();

        //Store the ID of the original window
        String originalWindow = driver.getWindowHandle();

        //Check we don't have other windows open already
        assert driver.getWindowHandles().size() == 1;

        //Click the link which opens in a new window
        //driver.switchTo().frame("sdk-iframe-login-box");
        driver.findElement(registerLinkLocator).click();
        //driver.switchTo().defaultContent();

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
        //driver.close();
    }
}
