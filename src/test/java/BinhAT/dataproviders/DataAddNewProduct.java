package BinhAT.dataproviders;

import org.testng.annotations.DataProvider;
import BinhAT.helpers.*;
import BinhAT.helpers.ExcelHelper;

public class DataAddNewProduct {
    @DataProvider(name = "data_provider_add_new_product")
    public Object[][] dataAddProduct() {
        ExcelHelper excelHelpers = new ExcelHelper();
        Object[][] data = excelHelpers.getDataHashTable(SystemsHelper.getCurrentDir() + PropertiesHelper.getValue("EXCEL_CMS_ADD_PRODUCT"), "AddProduct", 1, 1);
        return data;
    }
}

