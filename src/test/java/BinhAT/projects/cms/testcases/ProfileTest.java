package BinhAT.projects.cms.testcases;

import BinhAT.common.BaseTest;
import BinhAT.helpers.ExcelHelper;
import BinhAT.helpers.PropertiesHelper;
import BinhAT.projects.cms.pages.LoginPage;
import BinhAT.projects.cms.pages.users.profilepage.ManageProfilePage;
import org.testng.annotations.Test;


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
