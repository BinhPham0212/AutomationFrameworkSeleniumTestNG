package BinhAT.pages.users;

import BinhAT.keywords.WebUI;
import static BinhAT.keywords.WebUI.*;
import BinhAT.pages.customers.CustomerPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class DashboardPage {
    //**Objects */

   public By titleDashboard = By.xpath("//h1[normalize-space()='Dashboard']");
   public static By menuHome = By.xpath("//a[contains(text(),'Home')]");
   public By inputsearch = By.xpath("//input[@id='search']");
   public By buttonSubmitSearch = By.xpath("//div[@class='input-group']//button[@type='submit']");

}
