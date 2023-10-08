package BinhAT.pages.users;

import org.openqa.selenium.By;

public class DashboardPage {
    //**DashboardPage Objects Page*/

   public By titleDashboard = By.xpath("//h1[normalize-space()='Dashboard']");
   public static By menuHome = By.xpath("//a[contains(text(),'Home')]");
   public By inputSearchProduct = By.xpath("//input[@id='search']");
   public By buttonSubmitSearch = By.xpath("//div[@class='input-group']//button[@type='submit']");

}
