package Brite_ERP;

import com.vytrack.utilities.ConfigurationReader;
import com.vytrack.utilities.VytrackUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CRM_Module {

    WebDriver driver;
    WebDriverWait wait;
    String opportunityTitleLocator = "//label[text()='Opportunity Title']/../..//input";
    String customerTitleLocator = "//*[contains(text(),'Customer')]/../following-sibling::td//input";
    String customerDropDownLocator = " //li[@id='ui-id-27']";

    String expectedRevenueLocator = "//label[text()='Expected Revenue']/../..//input";
    String createBtnLocator = "//span[text()='Create']";
    String treeStars = "//*[contains(text(),'Priority')]/../following-sibling::td//a[@title='Very High']";


    @BeforeMethod
    public void setUpClass() throws InterruptedException {


        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://54.148.96.210");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);

    }

   @AfterMethod
   public void tearUp() throws InterruptedException {
        Thread.sleep(4000);
        driver.quit();
    }

    @Test
    public void crmPrecondition() throws InterruptedException {
        driver.findElement(By.xpath("//b[contains(text(),'Sign in')]")).click();
        driver.findElement(By.id("login")).sendKeys("eventscrmmanager13@info.com");
        driver.findElement(By.id("password")).sendKeys("eventscrmmanager" + Keys.ENTER);
        Thread.sleep(4000);

        // Click on CRM
        driver.findElement(By.xpath("//span[@class='oe_menu_text'][contains(text(),'CRM')]")).click();

        //click on create
        driver.findElement(By.xpath("//button[@class='btn btn-primary btn-sm o-kanban-button-new']")).click();

        //create card opportunity name
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(opportunityTitleLocator))));
        driver.findElement(By.xpath(opportunityTitleLocator)).sendKeys("TV");


        // create customer
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(customerTitleLocator))));
        driver.findElement(By.xpath(customerTitleLocator)).sendKeys("Lisa"+Keys.ENTER);

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(expectedRevenueLocator)))).sendKeys("75,000");

        driver.findElement(By.xpath(treeStars)).click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(createBtnLocator)))).click();






    }

    @Test(dependsOnMethods = "crmPrecondition")
    public void CRMmoduleTEST() throws InterruptedException {
        //Log in
        driver.findElement(By.xpath("//b[contains(text(),'Sign in')]")).click();
        driver.findElement(By.id("login")).sendKeys("eventscrmmanager13@info.com");
        driver.findElement(By.id("password")).sendKeys("eventscrmmanager" + Keys.ENTER);
        Thread.sleep(4000);

        // Click on CRM

        driver.findElement(By.xpath("//span[@class='oe_menu_text'][contains(text(),'CRM')]")).click();
        Thread.sleep(4000);
        //click on Pipeline

        //view items in a list view
        driver.findElement(By.xpath("//button[@class='btn btn-icon fa fa-lg fa-list-ul o_cp_switch_list']")).click();
        Thread.sleep(4000);

        String opportunity = "TV";
        String locatorToSelectOpportunity = "//td[text()='"+opportunity+"']/preceding-sibling::td//input";
        driver.findElement(By.xpath(locatorToSelectOpportunity)).click();

        driver.findElement(By.xpath("//button[contains(text(),'Action')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Delete')]")).click();
        driver.findElement(By.xpath("//*[contains(text(),'Ok')]")).click();
        Thread.sleep(3000);
        Assert.assertTrue( driver.findElements(By.xpath(locatorToSelectOpportunity)).size() == 0);

    }




    public void selectPriority(String priority) {
        String locator = "//label[text()='Priority']/../..//a[@title='" + priority + "']";
        driver.findElement(By.xpath(locator)).click();

    }



    public void selectOpportunityByCustomerName(String customerName){
        String locator = "//td[text()='"+customerName+"']/preceding-sibling::td//input";
        driver.findElement(By.xpath(locator)).click();
    }
}
