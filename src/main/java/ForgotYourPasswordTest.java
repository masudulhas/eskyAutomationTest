import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class ForgotYourPasswordTest {
    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = Base.getDriver();

        //Locators
        By loginLinkLocator = By.className("account-title");
        By forgotYourPassword = By.xpath("//a[@class=\"toggle-reset-link\"]");
        By emailLinkLocator = By.xpath("//input[@type=\"email\"]");
        By submitLocator = By.xpath("//button[@type=\"submit\"]");

        // launch chrome and execute the test steps
        driver.findElement(loginLinkLocator).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@class='menu-item user-zone-email']")).click();
        driver.findElement(forgotYourPassword).click();
        Thread.sleep(4000);
        driver.findElement(emailLinkLocator).sendKeys("masudul@gmail.com");
        driver.findElement(submitLocator).click();

        // get the actual value of the title
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
