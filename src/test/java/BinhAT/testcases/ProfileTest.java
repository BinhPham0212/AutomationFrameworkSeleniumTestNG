package BinhAT.testcases;

import BinhAT.common.BaseTest;
import BinhAT.drivers.DriverManager;
import BinhAT.helpers.ExcelHelper;
import BinhAT.helpers.PropertiesHelper;
import BinhAT.pages.LoginPage;
import BinhAT.pages.users.profilepage.ManageProfilePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import static BinhAT.keywords.WebUI.*;

public class ProfileTest extends BaseTest {
    LoginPage loginPage = new LoginPage();
    ManageProfilePage manageProfilePage = new ManageProfilePage();
    ExcelHelper excel = new ExcelHelper();

    @Test
    public void TC_AddNewAddress() {
        excel.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("EMAIL", 2), excel.getCellData("PASSWORD", 2));
        manageProfilePage.addNewAddress();
        manageProfilePage.verifyNewAddressAdded();
    }

    @Test
    public void TC_verifyContentAddNewAddress() {
        excel.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("EMAIL", 2), excel.getCellData("PASSWORD", 2));
        clickElement(manageProfilePage.menuManageProfile);

//        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//        js.executeScript("arguments[0].style.border='5px solid blue'", DriverManager.getDriver().findElement(By.xpath("//div[@class='aiz-user-panel']/preceding-sibling::div[contains(@class,'aiz-user-sidenav')]//a[contains(normalize-space(),'Manage Profile')]")));
//        sleep(3);

        manageProfilePage.verifyNewAddressAdded();
    }

}
