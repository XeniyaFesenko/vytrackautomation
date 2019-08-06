package com.vytrack.tests.components;

import com.vytrack.utilities.VytrackUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest_positive {


    WebDriver driver;

    @BeforeMethod
    public void setUpClass() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://qa3.vytrack.com");




    }

    @AfterMethod

    public void tearDown(){

        driver.quit();
    }



        @Test
        public void loginAsStoreManager () throws InterruptedException {

            // Login as a store manager
            VytrackUtils.login(driver, "storemanager53", "UserUser123");

            Thread.sleep(2000);

            //Verify the name of the store manager is displayed on top right
            String expectedName = "Aditya Rempel";
            String actualName = driver.findElement(By.cssSelector("#user-menu > a")).getText().trim();
            Assert.assertEquals(expectedName, actualName);

            Thread.sleep(2000);


            //verify dashboard pageName is open

            String expectedPagename = "Dashboard";
            String actualPagename = driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
            Assert.assertEquals(expectedPagename, actualPagename);

            Thread.sleep(2000);

            //Log out

            driver.findElement(By.cssSelector("#user-menu > a")).click();
            Thread.sleep(2000);

            driver.findElement(By.linkText("Logout")).click();
            Thread.sleep(2000);

            //Log in to Vytrack as a salesmanager
            VytrackUtils.login(driver, "salesmanager104", "UserUser123");
            Thread.sleep(2000);


            //verify dashboard page is open
            Thread.sleep(2000);
            String expectedPagename1 = "Dashboard";
            String actualPagename1= driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
            Assert.assertEquals(expectedPagename1, actualPagename1);

            Thread.sleep(2000);

            //A different   name    should  be  displayed   on top roght
            String actualName2 =driver.findElement(By.cssSelector("#user-menu > a")).getText().trim();
            Assert.assertNotEquals(actualName2, expectedName);
            Thread.sleep(2000);


            //log out
            driver.findElement(By.cssSelector("#user-menu > a")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("Logout")).click();
            Thread.sleep(2000);


            //Log in as a truck driver
            VytrackUtils.login(driver,"user4","UserUser123");
            Thread.sleep(2000);

            //Verify	Dashboad/Quick	Launchpad	page	is	open
            String expectedPageName3 = "Quick Launchpad";
            String actualPageName3 =driver.findElement(By.xpath("//h1[@class='oro-subtitle']")).getText();
            Assert.assertEquals(expectedPageName3,actualPageName3);
            Thread.sleep(2000);


            //A different   name    should  be  displayed   on
            String actualName3 =driver.findElement(By.cssSelector("#user-menu > a")).getText().trim();
            Assert.assertNotEquals(actualName3, actualName);
            Assert.assertNotEquals(actualName3, actualName2);
            Thread.sleep(2000);


    }
    }
