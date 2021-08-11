import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class EditContactDetailsTest {

    public static void main(String[] args) throws InterruptedException{

        ChromeDriver driver = BaseLogin.getDriver();

        //Locators
        By userNameLocator = By.xpath("//span[@class='account-title']");
        By payerDetailsLocator = By.xpath("//div[@class=\"user-zone-menu-links\"]//a[text()='Payer details']");
        By editDetailsLocator = By.xpath("//div[@class=\"actions\"]");
        By countryCodeLocator = By.xpath("//ps-input-container[@fieldname=\"countryCode\"]//select");
        By phoneNumberLocator = By.xpath("//ps-input-container[@fieldname=\"number\"]//input");
        By saveDetailsLocator = By.xpath("(//button[@class=\"button primary full-width\"]//div[text() ])[1]");

        // launch chrome and execute the test steps
        driver.findElement(userNameLocator).click();
        driver.findElement(payerDetailsLocator).click();
        driver.findElement(editDetailsLocator).click();
        WebElement countryCode = driver.findElement(countryCodeLocator);
        Select selectCountryCode = new Select(countryCode);
        selectCountryCode.selectByVisibleText("United Kingdom (+44)");
        driver.findElement(phoneNumberLocator).sendKeys("0741779405");
        driver.findElement(saveDetailsLocator).click();

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
