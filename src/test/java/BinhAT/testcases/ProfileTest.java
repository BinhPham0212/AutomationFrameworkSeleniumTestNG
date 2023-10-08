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

}
