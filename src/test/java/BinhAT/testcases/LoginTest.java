package BinhAT.testcases;

import BinhAT.common.BaseTest;
import BinhAT.dataproviders.DataLogin;
import BinhAT.drivers.DriverManager;
import BinhAT.helpers.CaptureHelper;
import BinhAT.helpers.ExcelHelper;
import BinhAT.helpers.PropertiesHelper;
import BinhAT.listeners.TestListener;
import BinhAT.pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Hashtable;

@Listeners(TestListener.class)         //Goi class Listener mới thiết lập
public class LoginTest extends BaseTest {

    //Initialize objects
    LoginPage loginPage = new LoginPage();
    ExcelHelper excel = new ExcelHelper();

    @Test(priority = 1)
    public void TC_loginSuccessWithCustomerAccount() {
        excel.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginSuccessWithCustomerAccount(excel.getCellData("EMAIL", 2), excel.getCellData("PASSWORD", 2));
    }

    @Test(priority = 2)
    public void TC_loginFailWithEmailNull() {
        excel.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginFailWithEmailNull(excel.getCellData("PASSWORD", 2));
    }

    @Test(priority = 3)
    public void TC_loginFailWithInvalidEmail() {
        excel.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginFailWithInvalidEmail(excel.getCellData("EMAIL",3), excel.getCellData("PASSWORD", 3));
    }

    @Test(priority = 4)
    public void TC_loginFailWithInvalidPassword() {
        excel.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginFailWithInvalidPassword(excel.getCellData("EMAIL",4), excel.getCellData("PASSWORD", 4));
    }

    @Test(priority = 5)
    public void TC_loginFailWithPasswordNull() {
        excel.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginFailWithPasswordNull(excel.getCellData("EMAIL",4));
    }

}


