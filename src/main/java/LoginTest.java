import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;
public class LoginTest {
    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = BaseLogin.getDriver();

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