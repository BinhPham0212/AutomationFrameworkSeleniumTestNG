package BinhAT.projects.cms.testcases;

import BinhAT.common.BaseTest;
import BinhAT.helpers.ExcelHelper;
import BinhAT.helpers.PropertiesHelper;
import BinhAT.listeners.TestListener;
import BinhAT.projects.cms.pages.LoginPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)         //Goi class Listener mới thiết lập
public class LoginTest extends BaseTest {

    //Initialize objects
    @Test
    public void TC_loginFailWithInvalidEmail() {
        LoginPage loginPage = new LoginPage();
        ExcelHelper excel = new ExcelHelper();
        excel.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginFailWithInvalidEmail(excel.getCellData("EMAIL",3), excel.getCellData("PASSWORD", 3));
    }

    @Test
    public void TC_loginSuccessWithCustomerAccount() {
        LoginPage loginPage = new LoginPage();
        ExcelHelper excel1 = new ExcelHelper();
        loginPage.loginSuccessWithCustomerAccount(excel1.getCellData("EMAIL", 2), excel1.getCellData("PASSWORD", 2));
    }

    @Test
    public void TC_loginFailWithEmailNull() {
        LoginPage loginPage = new LoginPage();
        ExcelHelper excel2 = new ExcelHelper();
        excel2.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginFailWithEmailNull(excel2.getCellData("PASSWORD", 2));
    }

    @Test
    public void TC_loginFailWithInvalidPassword() {
        LoginPage loginPage = new LoginPage();
        ExcelHelper excel3 = new ExcelHelper();
        excel3.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginFailWithInvalidPassword(excel3.getCellData("EMAIL",4), excel3.getCellData("PASSWORD", 4));
    }

    @Test
    public void TC_loginFailWithPasswordNull() {
        LoginPage loginPage = new LoginPage();
        ExcelHelper excel4 = new ExcelHelper();
        excel4.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginFailWithPasswordNull(excel4.getCellData("EMAIL",4));
    }

}


