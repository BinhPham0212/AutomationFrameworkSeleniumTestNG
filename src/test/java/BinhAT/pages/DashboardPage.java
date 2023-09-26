package BinhAT.pages;

import BinhAT.keywords.WebUI;
import static BinhAT.keywords.WebUI.*;
import BinhAT.pages.customers.CustomerPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class DashboardPage {
    //**Data trong nội bộ trang Dashboard */
    private String PAGE_URL = "https://crm.anhtester.com/admin/";
    private String PAGE_TEXT = "Dashboard Options";


    //**Các Objects */

    By menuCustomer = By.xpath("//span[normalize-space()='Customers']");
    By buttonOptionDashboard = By.xpath("//div[@class='screen-options-btn']");
    By areaOptionDashboard = By.xpath("//div[@class='screen-options-area']");

    By widgetStatistics = By.xpath("//div[@id='widget-top_stats']");
    By checkboxQuickStatistics = By.xpath("//label[normalize-space()='Quick Statistics']");


    //Hàm constructor

    //**Hàm xử lý */
    public void verifyDashboardPage() {
        //Kiểm tra URL chứa phần thuộc trang Dashboard
        //Kiểm tra Text hoặc Object thuộc trang
        Assert.assertEquals(getCurrentUrl(), PAGE_URL, "URL chưa đúng trang Dashboard.");
        Assert.assertTrue(checkElementExist(buttonOptionDashboard),"Dashboard Options not exiting.");
        Assert.assertTrue(verifyElementVisible(buttonOptionDashboard, 5),"Dashboard Options not visible.");
    }
    public CustomerPage openCustomerPage() {
        waitForPageLoaded();
        clickElement(menuCustomer);

        return new CustomerPage();
    }

    public void verifyWidgetDashboardOptionsButton() {
        clickElement(buttonOptionDashboard);
        String expectedOptionsArea = getAttributeELement(areaOptionDashboard,"style");
        Assert.assertTrue(WebUI.checkElementExist(areaOptionDashboard),"Options Dashboard not exist");
    }

    public void clickCheckboxQuickStatistics() {
        clickElement(buttonOptionDashboard);
//        waitForElementVisible(checkboxQuickStatistics, 5); Bỏ wait roi rạc
        clickElement(buttonOptionDashboard);
    }

    public void verifyFilterStatistics() {
        SoftAssert softAssert = new SoftAssert();
        //Kiểm tra widget đang hiển thị (visible)
        softAssert.assertTrue(verifyElementVisible(widgetStatistics,5),"The Widget Statistics default are visible.");
        //Click unchecked this widget
        clickCheckboxQuickStatistics();
        //Kiểm tra widget trên đã bi ẩn (not visible)
        softAssert.assertTrue(verifyElementNotVisible(widgetStatistics,5),"The Widget Statistics default are not visible.");

    }









}
