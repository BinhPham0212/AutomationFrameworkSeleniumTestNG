package BinhAT.testcases;

import BinhAT.common.BaseTest;
import BinhAT.pages.customers.CustomerPage;
import BinhAT.pages.DashboardPage;
import BinhAT.pages.LoginPage;
import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;
    @Test
    public void testTotalOnDashboard() {
        loginPage = new LoginPage();
        //Liên kết trang được xảy ra nhờ hàm login trả về là sự khởi tạo của trang Dashboard
        dashboardPage = loginPage.login("admin@example.com","123456");
        //Kiểm tra trang Dashboard là đúng
        dashboardPage.verifyDashboardPage();


        //Kiểm tra menu customers
        customerPage = dashboardPage.openCustomerPage();
        customerPage.verifyCustomerPage();
    }

    @Test
    public void testDashboardOptionbutton() {
        loginPage = new LoginPage();
        loginPage.login("admin@example.com","123456");
        //Kiểm tra trang Dashboard là đúng
        dashboardPage = new DashboardPage();
        dashboardPage.verifyDashboardPage();
        dashboardPage.verifyWidgetDashboardOptionsButton();
    }

    @Test
    public void testFilterQuickStatisticsWidgetOnDashboard() {
        loginPage = new LoginPage();
        loginPage.login("admin@example.com","123456");
        //Kiểm tra trang Dashboard là đúng
        dashboardPage = new DashboardPage();
        dashboardPage.verifyDashboardPage();
        dashboardPage.verifyFilterStatistics();
    }


}
