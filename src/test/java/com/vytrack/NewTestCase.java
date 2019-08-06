package com.vytrack;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NewTestCase {


    public class MenuOptions {


        WebDriver driver;

        @BeforeMethod
        public void Class1() {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("http://qa3.vytrack.com");
        }


        @Test
        public void AsStoreManager1() throws InterruptedException {

            driver.findElement(By.id("prependedInput")).sendKeys("driver4");
            Thread.sleep(1000);
            driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
            Thread.sleep(1000);
            driver.findElement(By.id("_submit")).click();

            Thread.sleep(2000);


            String actual = driver.getTitle();
            String expected = "Sales menu should not be displayed";



            Assert.assertEquals(actual, expected);
 





        }
    }
}

