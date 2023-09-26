package BinhAT.testcases;

import BinhAT.common.BaseTest;
import BinhAT.keywords.WebUI;
import BinhAT.pages.customers.AddCustomerPage;
import BinhAT.pages.customers.CustomerDetailPage;
import BinhAT.pages.customers.CustomerPage;
import BinhAT.pages.DashboardPage;
import BinhAT.pages.LoginPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

// Kế thừa basetest để khởi tạo Browser
public class CustomerTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboardPage;
    CustomerPage customerPage;   //Khai báo object page
    AddCustomerPage addCustomerPage;
    CustomerDetailPage customerDetailPage;

    @Test
    public void testAddNewCustomer() {
        loginPage = new LoginPage();   //Khởi tạo object
        dashboardPage = loginPage.login("admin@example.com","123456");    //Trả về khởi tạo là Dashboard page
        customerPage = dashboardPage.openCustomerPage();       //Trả về khởi tạo là Customer page, customerPage là object được khai báo đại diện cho Customer Page
        //Kiểm tra xem Customer page load được và đúng hay chưa
        customerPage.verifyCustomerPage();
        addCustomerPage = customerPage.clickNewCustomerButton();

        String companyName = "Bình Gold12";   //Biến cục bộ
        String expectedCustomerName = "Bình Gold12";
        addCustomerPage.AddDataNewCustomer(companyName);

        //Mở lại trang Customer
        dashboardPage.openCustomerPage();
        //Search gia tri customer vua Add
        customerPage.searchCustomer(companyName);
        //Click vào giá trị Customer Name dòng đầu tiên
        customerDetailPage = customerPage.clickOnFristRowCustomerName();
        //Check Customer details
        customerDetailPage.checkCustomerDetail(expectedCustomerName);

    }

    @Test
    public void testEditNewCustomer() {
        loginPage = new LoginPage();   //Khởi tạo object
        dashboardPage = loginPage.login("admin@example.com","123456");    //Trả về khởi tạo là Dashboard page
        customerPage = dashboardPage.openCustomerPage();       //Trả về khởi tạo là Customer page, customerPage là object được khai báo đại diện cho Customer Page
        //Kiểm tra xem Customer page load được và đúng hay chưa
        customerPage.verifyCustomerPage();
        //Search gia tri customer vua Add
        String customerName = "Bình Gold12";   //Biến cục bộ
        String expectedCustomerName = "Bình Gold12";
        customerPage.searchCustomer(customerName);
        //Check Customer details
//        customerDetailPage.checkCustomerDetail(expectedCustomerName);
        //Click vào giá trị Customer Name dòng đầu tiên
        customerDetailPage = customerPage.clickOnFristRowCustomerName();
        String editCountryField = "Canada";
        String countryFieldExpected = "Canada";
        customerDetailPage.editCustomerDetail(editCountryField);
        customerDetailPage.checkAfterEditCustomerDetail(editCountryField, countryFieldExpected);
    }


}
