package BinhAT.testcases;

import BinhAT.common.BaseTest;
import BinhAT.helpers.ExcelHelper;
import BinhAT.helpers.PropertiesHelper;
import BinhAT.pages.LoginPage;
import BinhAT.pages.users.orders.OrderPage;
import org.testng.annotations.Test;

import static BinhAT.keywords.WebUI.*;

public class OrderTest extends BaseTest {
    LoginPage loginPage = new LoginPage();
    OrderPage orderPage = new OrderPage();

    @Test
    public void TC_AddToCart() {
        ExcelHelper excelLogin = new ExcelHelper();
        excelLogin.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("EMAIL", 2), excelLogin.getCellData("PASSWORD", 2));
        orderPage.addToCart("Product_02",3);
        clickElement(orderPage.buttonBackToShopping);
        orderPage.addToCart("Product_03",1);
    }

    @Test
    public void TC_CheckOutOrder() {
        ExcelHelper excelLogin = new ExcelHelper();
        excelLogin.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("EMAIL", 2), excelLogin.getCellData("PASSWORD", 2));
        orderPage.clearCartItems();
        orderPage.addToCart("Product_02",2);
        clickElement(orderPage.buttonBackToShopping);
        orderPage.addToCart("Product_03",2);
        clickElement(orderPage.buttonProceedToCheckout);
        orderPage.checkoutOrder();
        orderPage.verifyCodePurchase();
    }

}
