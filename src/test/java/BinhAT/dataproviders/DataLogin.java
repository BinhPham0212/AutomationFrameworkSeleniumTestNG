package BinhAT.dataproviders;

import BinhAT.helpers.ExcelHelper;
import BinhAT.helpers.SystemsHelper;
import org.testng.annotations.DataProvider;

public class DataLogin {
    @DataProvider(name = "dataProviderLoginCRM", parallel = true)
    public Object[][] dataLoginCRM() {
        return new Object[][]{
                {"admin@example.com", "123456"},
                {"admin@example.com", "123456"},
                {"admin@example.com", "123456"}
        };
    }

    @DataProvider(name = "data_provider_login_excel", parallel = true)
    public Object[][] dataLoginHRMFromExcel(String sheetName) {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getExcelData(SystemsHelper.getCurrentDir() + "src/test/Resources/datatest/CRM.xlsx", "Login");
        System.out.println("Login Data from Excel: " + data);
        return data;
    }

    @DataProvider(name = "data_provider_login_excel_custom_row", parallel = true)
    public Object[][] dataLoginCRMFromExcelCustomRow() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable(SystemsHelper.getCurrentDir() + "src/test/Resources/datatest/CRM.xlsx", "Login",3,3);
        System.out.println("Login Data from Excel: " + data);

        excelHelper.setExcelFile("src/test/Resources/datatest/CRM.xlsx","Login");
        //gọi hàm "Login"
        excelHelper.setCellData("Passed",3,"RESULT");
        return data;
    }

    @DataProvider(name = "data_provider_login_successful", parallel = true)
    public Object[][] dataLoginCRMWithSuccessful() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable(SystemsHelper.getCurrentDir() + "src/test/Resources/datatest/CRM.xlsx", "Login",1,2);
        System.out.println("Login Data from Excel: " + data);

        excelHelper.setExcelFile("src/test/Resources/datatest/CRM.xlsx","Login");
        //ghi kết quả test vào excel
        excelHelper.setCellData("Passed",1,"RESULT");
        excelHelper.setCellData("Passed",2,"RESULT");
        return data;
    }

    @DataProvider(name = "data_provider_login_invalidemail", parallel = true)
    public Object[][] dataLoginCRMWithInvalidEmail() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable(SystemsHelper.getCurrentDir() + "src/test/Resources/datatest/CRM.xlsx", "Login",3,3);
        System.out.println("Login Data from Excel: " + data);

        excelHelper.setExcelFile("src/test/Resources/datatest/CRM.xlsx","Login");
        //ghi kết quả test vào excel
        excelHelper.setCellData("Failed",3,"RESULT");
        return data;
    }

    @DataProvider(name = "data_provider_login_invalidpassword", parallel = true)
    public Object[][] dataLoginCRMWithInvalidPassword() {
        ExcelHelper excelHelper = new ExcelHelper();
        Object[][] data = excelHelper.getDataHashTable(SystemsHelper.getCurrentDir() + "src/test/Resources/datatest/CRM.xlsx", "Login",4,4);
        System.out.println("Login Data from Excel: " + data);

        excelHelper.setExcelFile("src/test/Resources/datatest/CRM.xlsx","Login");
        //ghi kết quả test vào excel
        excelHelper.setCellData("Failed",4,"RESULT");
        return data;
    }
}
