package BinhAT.pages.customers;

import BinhAT.keywords.WebUI;
import static BinhAT.keywords.WebUI.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddCustomerPage extends CustomerPage{
    //**Data trong nội bộ xác thực trang Customer */
    private String PAGE_URL = "https://crm.anhtester.com/admin/clients/client";
    private String PAGE_TEXT = "Customers Details";   //Chỉ trong trang này mới có

    //**Các Objects thuộc Customer page */
    public By tabCustomerDetail = By.xpath("//a[@aria-controls='contact_info']") ;
    public By inputCompanyName = By.xpath("//input[@id='company']") ;
    public By inputVat = By.xpath("//input[@id='vat']") ;
    public By inputPhoneNumber = By.xpath("//input[@id='phonenumber']") ;
    public By inputWebsite = By.xpath("//input[@id='website']") ;
    public By dropdownGroups = By.xpath("//label[@for='groups_in[]']/following-sibling::div") ;
    public By inputGroups = By.xpath("//label[@for='groups_in[]']/following-sibling::div//input[@type='search']") ;
    public By dropdownCurrency = By.xpath("//button[@data-id='default_currency']");

    public By dropdownLanguage = By.xpath("//button[@data-id='default_language']");
    public By inputAddress = By.xpath("//textarea[@id='address']");
    public By inputCity = By.xpath("//input[@id='city']");
    public By inputState = By.xpath("//input[@id='state']");
    public By inputZipcode = By.xpath("//input[@id='zip']");
    public By dropdownCountry = By.xpath("//label[@for='country']//following-sibling::div");
    public By inputCountry = By.xpath("//label[@for='country']//following-sibling::div//input[@type='search']");
    public By buttonSave = By.xpath("//div[@id='profile-save-section']//button[normalize-space()='Save']");

    //**Hàm xử lý cho các chức năng thuộc Customer Page */
    public void selectGroup(String nameGroups) {
        clickElement(dropdownGroups);
        getWebElement(inputGroups).sendKeys(nameGroups, Keys.ENTER);
        clickElement(dropdownGroups);
    }

    public void AddDataNewCustomer(String companyName) {
//        waitForPageLoaded();

        setText(inputCompanyName, companyName);
        setText(inputVat,"10");
        setText(inputPhoneNumber,"0368922198");
        setText(inputWebsite,"https://crm.anhtester.com/");
        selectGroup("Gold");
        setText(inputAddress,"Viet Nam");
        setText(inputCity,"Thai Binh");
        setText(inputState,"Thai Binh");
        setText(inputZipcode,"Thai Binh");
        clickElement(dropdownCountry);
        getWebElement(inputCountry).sendKeys("Vietnam", Keys.ENTER);
        clickElement(buttonSave);

        waitForPageLoaded();
    }

}
