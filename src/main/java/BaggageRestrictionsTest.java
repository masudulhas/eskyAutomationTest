import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BaggageRestrictionsTest {

    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = BaseLogin.getDriver();

        //Locators
        By moreTabLocator = By.xpath("//a[@class=\"menu-link more\"]");
        By travelGuide = By.xpath("//*[@id=\"main-menu-more-box\"]/li[1]/a");
        By ticketLocator = By.xpath("//span[text() =\"Airline tickets\"]");

        // launch chrome and execute the test steps
        driver.findElement(moreTabLocator).click();
        driver.findElement(travelGuide).click();
        driver.findElement(ticketLocator).click();
        driver.findElement(By.xpath("//a[text() =\"Baggage\"]")).click();
        driver.findElement(By.xpath("//a[text() = 'Baggage: size and weight restrictions']")).click();
        Thread.sleep(4000);
        WebElement airline = driver.findElement(By.xpath("//select[@id='articleRelatedSelect']"));
        Thread.sleep(4000);
        Select selectAirlines = new Select(airline);
        selectAirlines.selectByVisibleText("Emirates");
        //driver.findElement(By.xpath("//select[@id="articleRelatedSelect"]")).click();

        //Get the actual value of the title
        String actualTitle = driver.getTitle();
        System.out.println("actual title is: " + actualTitle);
        String expectedTitle = "Emirates - eSky.co.uk";

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
