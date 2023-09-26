package BinhAT.pages.customers;

import BinhAT.keywords.WebUI;

import static BinhAT.keywords.WebUI.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CustomerPage {
    //**Data trong nội bộ xác thực trang Customer */
    private String PAGE_URL = "https://crm.anhtester.com/admin/clients";
    private String PAGE_TEXT = "Customers Summary";   //Chỉ trong trang này mới có

    //**Các Objects thuộc Customer page */
    private By headerPageCustomer = By.xpath("(//div[@class='panel-body']//h4)[1]");
    //    By.xpath("//div[@class='panel-body']//h4[contains(.,'"+ PAGE_TEXT +"')]");
    private By buttonAddCustomer = By.xpath("//a[normalize-space()='New Customer']");
    private By buttonImportCustomer = By.xpath("//a[normalize-space()='Import Customers']");
    private By buttonContacts = By.xpath("//div[@class='_buttons']//a[normalize-space()='Contacts']");
    private By inputSearch = By.xpath("//div[@id='DataTables_Table_0_filter']//input");
    private By tdCustomerName = By.xpath("//table[@id='DataTables_Table_0']//tbody//tr[1]//td[3]/a");

    //**Hàm xử lý cho các chức năng thuộc Customer Page */
    public void verifyCustomerPage() {
        Assert.assertEquals(getCurrentUrl(), PAGE_URL, "URL not match with Customer page");
        Assert.assertTrue(checkElementExist(headerPageCustomer),"Header page Customer not existing");
        Assert.assertEquals(getTextELement(headerPageCustomer), PAGE_TEXT, "Header page of Customer Page not match.");
    }

    public AddCustomerPage clickNewCustomerButton() {
//        waitForElementVisible(buttonAddCustomer,10);
        clickElement(buttonAddCustomer);

        return new AddCustomerPage();
    }

    public void searchCustomer(String companyName) {
        waitForPageLoaded();
//        waitForElementVisible(inputSearch,10);
        setText(inputSearch,companyName);
//        waitForElementVisible(inputSearch,2);
        sleep(2);

    }

    public CustomerDetailPage clickOnFristRowCustomerName() {
        waitForPageLoaded();
//        waitForElementVisible(tdCustomerName,10);
        clickElement(tdCustomerName);

        return new CustomerDetailPage();
    }


}
