import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class TravelGuideTest {
    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = BaseLogin.getDriver();

        //Locators
        By moreTabLocator = By.xpath("//a[@class=\"menu-link more\"]");
        By travelGuide = By.xpath("//*[@id=\"main-menu-more-box\"]/li[1]/a");

        // launch chrome and execute the test steps
        driver.findElement(moreTabLocator).click();
        driver.findElement(travelGuide).click();

        //Get the actual value of the title
        String actualTitle = driver.getTitle();
        System.out.println("actual title is: " + actualTitle);
        String expectedTitle = "Travel guide - useful travel tips - FAQ - eSky.co.uk";

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
