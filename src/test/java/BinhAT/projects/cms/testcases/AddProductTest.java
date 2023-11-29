package BinhAT.projects.cms.testcases;

import BinhAT.common.BaseTest;
import BinhAT.dataproviders.DataAddNewProduct;
import BinhAT.helpers.ExcelHelper;
import BinhAT.helpers.PropertiesHelper;
import BinhAT.projects.cms.pages.admin.AddProductPage;
import BinhAT.projects.cms.pages.LoginPage;
import org.testng.annotations.Test;

import java.util.Hashtable;


public class AddProductTest extends BaseTest {
    LoginPage loginPage = new LoginPage();
    AddProductPage addProduct = new AddProductPage();


    @Test(dataProvider = "data_provider_add_new_product", dataProviderClass = DataAddNewProduct.class)
    public void TC_AddNewProduct(Hashtable<String, String> data) {
        ExcelHelper excelLogin = new ExcelHelper();
        ExcelHelper excelAddNewProduct = new ExcelHelper();

        excelLogin.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_LOGIN"), "Login");
        excelAddNewProduct.setExcelFile(PropertiesHelper.getValue("EXCEL_CMS_ADD_PRODUCT"), "AddProduct");

        loginPage.loginSuccessWithAdminAccount(excelLogin.getCellData("EMAIL", 1), excelLogin.getCellData("PASSWORD", 1));
        addProduct.addNewProduct(data.get("nameProduct"), data.get("category"), data.get("unit"), data.get("nameTags"),
                data.get("image"), data.get("unitPrice"), data.get("discountDate"), data.get("discount"),
                data.get("quantity"), data.get("description"));
        addProduct.verifyNewProductAddedSuccess(data.get("nameProduct"), data.get("unit"),data.get("description"));
    }
}
