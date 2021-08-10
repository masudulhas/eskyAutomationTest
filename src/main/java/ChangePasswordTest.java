import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChangePasswordTest {
    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = BaseLogin.getDriver();

        //Locators
        By userNameLocator = By.xpath("//span[@class='account-title']");
        By accountSettingsLocator = By.xpath("//div[@class=\"user-zone-menu-links\"]//a[text()='Account settings']");
        By changeLocator = By.xpath("//div[@class=\"box\"]//div[text() =\"Change\"]");
        By oldPasswordLocator = By.xpath("(//input[@type=\"password\"])[1]");
        By newPasswordLocator = By.xpath("(//input[@type=\"password\"])[2]");
        By changePasswordLocator = By.xpath("//button[@class=\"submit-button initial\"]");

        // launch chrome and execute the test steps
        driver.findElement(userNameLocator).click();
        driver.findElement(accountSettingsLocator).click();
        driver.findElement(changeLocator).click();
        driver.findElement(oldPasswordLocator).sendKeys("123abcZ");
        driver.findElement(newPasswordLocator).sendKeys("Zabc123");
        driver.findElement(changePasswordLocator).click();

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
