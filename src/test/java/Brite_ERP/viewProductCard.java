
package Brite_ERP;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class viewProductCard {



    WebDriver driver;

    @BeforeMethod
    public void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://54.148.96.210");
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

    }
    @AfterMethod
    public void tearUp() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }


    @Test
    public void viewProductCard() throws InterruptedException{

        driver.findElement(By.xpath("//b[contains(text(),'Sign in')]")).click();
        driver.findElement(By.id("login")).sendKeys("in_pos_user3@info.com");


        driver.findElement(By.id("password")).sendKeys("KjKtfgrs39");

        //User should be able to log in by entering correct credentials.
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        Thread.sleep(5000);

        //Section "Purchases" should be dispayed.
        driver.findElement(By.xpath("//span[contains(text(),'Purchases')]")).click();

        //By clicking on "Products" option user should be able to see entire list of the products that are available.
        driver.findElement(By.xpath("//a[@class='oe_menu_leaf' and @data-menu=\"516\"]")).click();

        // By clicking on a "Product" icon, user should be able to view products information card.
        driver.findElement(By.xpath("//div[@class='o_kanban_view o_kanban_ungrouped']//div[1]//div[1]//img[1]")).click();


    }





}
