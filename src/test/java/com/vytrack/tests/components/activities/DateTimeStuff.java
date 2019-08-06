package com.vytrack.tests.components.activities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DateTimeStuff {


    WebDriver driver;
    WebDriverWait wait;

    String StartDate = "//input[@id='date_selector_oro_calendar_event_form_start-uid-5d39d68ad8138']";

    @BeforeMethod
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://qa3.vytrack.com");
        driver.findElement(By.id("prependedInput")).sendKeys("user4");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123" + Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void tearUp() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }


    @Test
    public void DateTimeAndTimeTest1() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//span[@class='title title-level-1'][contains(text(),'Activities')]")).click();

        driver.findElement(By.xpath("//span[contains(text(),'Calendar Events')]")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[contains(@class,'btn main-group btn-primary pull-right')]")).click();
        Thread.sleep(3000);

        //driver.findElement(By.id("date_selector_oro_calendar_event_form_start-uid-5d39d68ad8138")).click();

    }

    @Test
    public void DateTimeAndTimeTest2() {

    }


}
