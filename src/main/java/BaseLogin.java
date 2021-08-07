import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BaseLogin {

    public static ChromeDriver getDriver() throws InterruptedException {

        ChromeDriver driver = Base.getDriver();

        //Locators
        By loginLinkLocator = By.className("account-title");
        By loginUserNameInputLocator = By.xpath("//input[contains(@placeholder, 'Your e-mail')]");
        By loginPasswordInputLocator = By.xpath("//input[contains(@type, 'password')]");
        By loginButtonLocator = By.xpath("//button[contains(@type, 'submit')]");

        // launch chrome and execute the test steps
        driver.findElement(loginLinkLocator).click();
        driver.findElement(By.xpath("//li[@class='menu-item user-zone-email']")).click();
        //driver.switchTo().frame("sdk-iframe-login-box");
        driver.findElement(loginUserNameInputLocator).sendKeys("masud33bd@gmail.com");
        driver.findElement(loginPasswordInputLocator).sendKeys("62320702Asba..");
        driver.findElement(loginButtonLocator).click();
        //driver.switchTo().defaultContent();
        Thread.sleep(4000);
        return  driver;
    }
}

