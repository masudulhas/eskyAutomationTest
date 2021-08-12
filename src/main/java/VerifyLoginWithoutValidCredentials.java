import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class VerifyLoginWithoutValidCredentials {

    public static void main(String[] args) throws InterruptedException{

        ChromeDriver driver = Base.getDriver();

        //Locators
        By loginLinkLocator = By.className("account-title");
        By loginUserNameInputLocator = By.xpath("//input[contains(@placeholder, 'Your e-mail')]");
        By loginPasswordInputLocator = By.xpath("//input[contains(@type, 'password')]");
        By loginButtonLocator = By.xpath("//button[contains(@type, 'submit')]");

        // launch chrome and execute the test steps
        driver.findElement(loginLinkLocator).click();
        driver.findElement(By.xpath("//li[@class='menu-item user-zone-email']")).click();
        driver.findElement(loginUserNameInputLocator).sendKeys("masudbd@gmail.com");
        driver.findElement(loginPasswordInputLocator).sendKeys("62320702Asba");
        driver.findElement(loginButtonLocator).click();
        Thread.sleep(4000);

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
