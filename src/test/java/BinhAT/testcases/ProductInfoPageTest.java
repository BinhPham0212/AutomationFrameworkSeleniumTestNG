package BinhAT.testcases;

import BinhAT.common.BaseTest;
import BinhAT.helpers.ExcelHelper;
import BinhAT.helpers.PropertiesHelper;
import BinhAT.pages.LoginPage;
import BinhAT.pages.users.products.ProductInfoPage;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ProductInfoPageTest extends BaseTest {
    LoginPage loginPage = new LoginPage();
    ProductInfoPage productInfoPage = new ProductInfoPage();

    @Test
    public void TC_GetProductInfo() {
        ExcelHelper excelLogin = new ExcelHelper();
        excelLogin.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        loginPage.loginSuccessWithCustomerAccount(excelLogin.getCellData("EMAIL", 2), excelLogin.getCellData("PASSWORD", 2));

        // Initialize Object and class Exelhelper
        ExcelHelper excelSetInfo = new ExcelHelper();
        excelSetInfo.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_PRODUCT_INFO"), "ProductInfo");
        ExcelHelper excelGetname = new ExcelHelper();
        excelGetname.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_PRODUCT_INFO"), "NameProductGet");

        ArrayList productInfo = productInfoPage.productInfo(excelGetname.getCellData("Name Product", 2));
        int lastRow = excelSetInfo.getLastRowNum();
        int newRow = lastRow + 1;
        excelSetInfo.setCellData(String.valueOf(newRow), newRow, 0);
        for (int i = 0; i < productInfo.size(); i++) {
            if (String.valueOf(newRow) != null) {
                excelSetInfo.setCellData(String.valueOf(productInfo.get(i)), newRow, i + 1);
            }
        }

    }


}
