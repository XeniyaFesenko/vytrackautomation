package com.vytrack.tests.components.login_navigation;

import com.vytrack.utilities.VytrackUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class LoginTest_negative {


    WebDriver driver;

    @BeforeMethod
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://qa3.vytrack.com");


    }
    @Test
            public void InvalidCridentials() throws InterruptedException {
            String expectedTitle = driver.getTitle();
             String expectedUrl = driver.getCurrentUrl();

            VytrackUtils.login(driver, "storemanager53", "User123");
            Thread.sleep(2000);

              String actualTitle = driver.getTitle();
              String actualUrl = driver.getCurrentUrl();

            String expectedMessage = "Invalid user name or password.";
            String actualMessage = driver.findElement(By.xpath(" //div[contains(text(),'Invalid user name or password.')]")).getText();
            Assert.assertEquals(expectedMessage, actualMessage);

            Assert.assertEquals(actualUrl, expectedUrl);
            Assert.assertEquals(actualTitle, expectedTitle);


        }





    }

