import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class CheapFlightsForRoundTripTest {
    public static void main(String[] args) throws InterruptedException {

        // Locators
        By loginLinkLocator = By.className("account-title");
        By cookiePopUpLocator =  By.xpath("//button[contains(@mode, 'primary')]");
        By loginUserNameInputLocator = By.xpath("//input[@placeholder='Your e-mail']");
        By loginPasswordInputLocator = By.xpath("//input[contains(@type, 'password')]");
        By loginButtonLocator = By.xpath("//button[contains(@type, 'submit')]");
        By cheapFlightLocator = By.xpath("//a[text()='Cheap flights']");

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
        //String baseUrl1 = "https://www.esky.co.uk/flights";

        // launch chrome and execute the test steps
        driver.get(baseUrl);
        driver.findElement(cookiePopUpLocator).click();
        driver.findElement(loginLinkLocator).click();
        driver.findElement(By.xpath("//li[@class='menu-item user-zone-email']")).click();
        //driver.switchTo().frame("sdk-iframe-login-box"); //Switch to frame
        driver.findElement(loginUserNameInputLocator).sendKeys("masud33bd@gmail.com");
        driver.findElement(loginPasswordInputLocator).sendKeys("62320702Asba..");
        driver.findElement(loginButtonLocator).click();
        //driver.switchTo().defaultContent();//Back to default content
        Thread.sleep(4000);

        //working from here 'Cheap flights'
        driver.findElement(cheapFlightLocator).click();
        Thread.sleep(4000);
        WebElement departure = driver.findElement(By.name("tr[0][d]"));
        departure.sendKeys("Heathrow"); //Departure from
        departure.sendKeys(Keys.ARROW_DOWN);
        departure.sendKeys(Keys.ENTER);
        WebElement arrival = driver.findElement(By.name("tr[0][a]"));
        arrival.sendKeys("Stockholm"); //Arrival to
        arrival.sendKeys(Keys.ARROW_DOWN);
        Thread.sleep(4000);
        arrival.sendKeys(Keys.ENTER);

        //Date picker for Departure and Arrival
        for(int i=0; i<2; i++) {
            if(i==0) driver.findElement(By.id("departureDateRoundtrip0")).click();
            else driver.findElement(By.id("departureDateRoundtrip1")).click();
            List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody//td"));

            for (WebElement element : allDates) {
                String date = element.getText();

                // once date is 15 then click and break
                if (date.equalsIgnoreCase("15") && i==0) {
                    element.click();
                    break;
                }
                // once date is 20 then click and break
                else if(date.equalsIgnoreCase("20") && i==1) {
                    element.click();
                    break;
                }
            }
        }
        //Thread.sleep(4000);
        //We have to wait for the Ajax loader to complete loading the all the WebElements
        //To achieve that we will introduce ExplicitWait
        //i.e. WebDriverWait with ExpectedConditions set to elementToBeClickable
        WebDriverWait wait = new WebDriverWait (driver, 30);
        driver.findElement(By.xpath("//div[(@data-content-id='pax-counter')]")).click();
        //Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("plus"))).click();
        //driver.findElement(By.className("plus")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//div[(@data-content-id='pax-counter')]")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[@class='btn transaction qsf-search']")).click();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='transaction-text']"))).click();

        // get the actual value of the title
        String actualTitle = driver.getTitle();
        System.out.println("actual title is: " + actualTitle);
        String expectedTitle = "London - All Airports - Stockholm - All Airports - eSky.co.uk";


        /*Compare the actual title of the page with the expected one and print
        The result as "Passed" or "Failed"*/
        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        // sleeping the execution for 4000 milly sec
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //close chrome
        //driver.close();
    }
}