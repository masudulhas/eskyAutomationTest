import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class CheapFlightsForRoundTripTest {
    public static void main(String[] args) throws InterruptedException {

        ChromeDriver driver = BaseLogin.getDriver();

        // Locators
        By cheapFlightLocator = By.xpath("//a[text()='Cheap flights']");

        // launch chrome and execute the test steps
        //working from here 'Cheap flights'
        driver.findElement(cheapFlightLocator).click();
        Thread.sleep(4000);
        WebElement departure = driver.findElement(By.name("tr[0][d]"));
        departure.sendKeys("Manchester"); //Departure from
        departure.sendKeys(Keys.ARROW_DOWN);
        departure.sendKeys(Keys.ENTER);
        driver.findElement(By.name("tr[0][a]")).sendKeys("Stockholm, Sweden"); //Arrival to
        driver.findElement(By.xpath("//a[@data-qsf='ARN!airport']")).click();

        //Date picker for Departure and Arrival
        for(int i=0; i<2; i++) {
            if(i==0) driver.findElement(By.id("departureDateRoundtrip0")).click();
            else driver.findElement(By.id("departureDateRoundtrip1")).click();
            List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody//td"));

            for (WebElement element : allDates) {
                String date = element.getText();

                // once date is 25 then click and break
                if (date.equalsIgnoreCase("25") && i==0) {
                    element.click();
                    break;
                }
                // once date is 28 then click and break
                else if(date.equalsIgnoreCase("28") && i==1) {
                    element.click();
                    break;
                }
            }
        }
        //After search flight
        //We have to wait for the Ajax loader to complete loading the all the WebElements
        //To achieve that we will introduce ExplicitWait
        //i.e. WebDriverWait with ExpectedConditions set to elementToBeClickable
        WebDriverWait wait = new WebDriverWait (driver, 50);
        //For several Customer
        /*driver.findElement(By.xpath("//div[(@data-content-id='pax-counter')]")).click();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(By.className("plus"))).click();
        //driver.findElement(By.className("plus")).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//div[(@data-content-id='pax-counter')]")).click();
        Thread.sleep(4000);*/
        driver.findElement(By.xpath("//button[@class='btn transaction qsf-search']")).click();
        Thread.sleep(4000);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='transaction-text']"))).click();
        Thread.sleep(4000);
        driver.findElement(By.xpath("//i[@class=\"icon-umbrella\"]")).click();

        //Customer details
        driver.findElement(By.xpath("//input[@id=\"bookFlight_paxes_1_name\"]")).sendKeys("Ateeb");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_paxes_1_surname\"]")).sendKeys("Hasan");
        WebElement title = driver.findElement(By.xpath("//select[@id='bookFlight_paxes_1_title']"));
        Select select = new Select(title);
        select.selectByVisibleText("Mr.");
        WebElement day = driver.findElement(By.xpath("//select[@id='bookFlight_paxes_1_additionalData_dateOfBirth_value_day']"));
        day.sendKeys("14");
        driver.findElement(By.xpath("//select[@id=\"bookFlight_paxes_1_additionalData_dateOfBirth_value_month\"]")).sendKeys("Jan");
        driver.findElement(By.xpath("//select[@id=\"bookFlight_paxes_1_additionalData_dateOfBirth_value_year\"]")).sendKeys("1980");
        driver.findElement(By.xpath("//input[@id='bookFlight_paymentDetails_cardNumber']")).sendKeys("1236547890123654");
        driver.findElement(By.xpath("//select[@id='bookFlight_paymentDetails_cardExpiryDate_month']")).sendKeys("12");
        driver.findElement(By.xpath("//select[@id='bookFlight_paymentDetails_cardExpiryDate_year']")).sendKeys("2025");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_paymentDetails_cardCvv\"]")).sendKeys("562");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_paymentDetails_cardHolderName\"]")).sendKeys("Ateeb Hasan");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_paymentDetails_zipCode\"]")).sendKeys("s47ad");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_paymentDetails_cityName\"]")).sendKeys("Sheffield");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_paymentDetails_street\"]")).sendKeys("London road");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_paymentDetails_houseNumber\"]")).sendKeys("400");

        //Invoice info payer
        driver.findElement(By.xpath("//input[@id=\"bookFlight_payerDetails_invoiceRequested\"]")).click();
        driver.findElement(By.xpath("//input[@id=\"bookFlight_payerDetails_firstName\"]")).sendKeys("Aribah");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_payerDetails_lastName\"]")).sendKeys("Hasan");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_payerDetails_zipCode\"]")).sendKeys("S46AF");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_payerDetails_cityName\"]")).sendKeys("Sheffield");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_payerDetails_street\"]")).sendKeys("London road");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_payerDetails_houseNumber\"]")).sendKeys("401");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_contactDetails_phoneNumber_phoneNumber\"]")).sendKeys("07217559503");
        driver.findElement(By.xpath("//input[@id=\"bookFlight_statute\"]")).click();
        driver.findElement(By.xpath("//button[text()=\"Book and pay\"]")).click();

        // get the actual value of the title
        String actualTitle = driver.getTitle();
        System.out.println("actual title is: " + actualTitle);
        String expectedTitle = "eSky: Manchester - Stockholm";
        String expectedTitle1 = "eSky: Manchester - Cracow";
        String expectedTitle2 = "eSky: Manchester (NH) - Stockholm";

        /*Compare the actual title of the page with the expected one and print
        The result as "Passed" or "Failed"*/
        if (actualTitle.contentEquals(expectedTitle)) {
            System.out.println("Test Passed!");
        }else if(actualTitle.contentEquals(expectedTitle1)){
            System.out.println("Test Passed!");
        }else if(actualTitle.contentEquals(expectedTitle2)){
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