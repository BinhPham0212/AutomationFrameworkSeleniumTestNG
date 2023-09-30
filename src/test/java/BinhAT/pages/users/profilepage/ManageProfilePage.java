package BinhAT.pages.users.profilepage;

import BinhAT.testcases.LoginTest;
import org.openqa.selenium.By;

import static BinhAT.keywords.WebUI.*;

public class ManageProfilePage {
    private By titleManageProfilePage = By.xpath("//h1[normalize-space()='Manage Profile']");
    public By menuManageProfile = By.xpath("//div[@class='aiz-user-panel']/preceding-sibling::div[contains(@class,'aiz-user-sidenav')]//a[contains(normalize-space(),'Manage Profile')]");
    private By titleAddress = By.xpath("//h5[normalize-space()='Address']");
    private By buttonAddressNewAddress = By.xpath("//div[contains(@onclick,'add_new_address')]");
    private By titlePopupNewAddress = By.xpath("//div[@id='new-address-modal']//h5[@class='modal-title']");

    private By inputYourAddress = By.xpath("//div[@class='modal-body']//textarea[@placeholder='Your Address']");
    private By selectCountry = By.xpath("//button[@title='Select your country']");
    private By inputSearchCountry = By.xpath("//button[@title='Select your country']//following-sibling::div//input");
    private By selectState = By.xpath("//div[contains(text(),'Select State')]");
    private By inputSearchState = By.xpath("//button[@title='Select State']//following-sibling::div//input");
    private By selectCity = By.xpath("//select[@name='city_id']//following-sibling::button");
    private By inputSearchCity = By.xpath("//select[@name='city_id']//following-sibling::div//input");
    private By inputPostalCode = By.xpath("//input[@placeholder='Your Postal Code']");
    private By inputPhoneAddress = By.xpath("//input[@placeholder='+880']");
    private By buttonSaveNewAddress = By.xpath("//button[normalize-space()='Save']");
    private By divFirstAddNewAddress = By.xpath("//div[contains(@onclick,'add_new_address')]//preceding-sibling::div[1]");
//    private By buttonEditNewAddress = By.xpath("//div[contains(@onclick,'add_new_address')]//preceding-sibling::div[1]//button//following-sibling::div/a[normalize-space()='Edit']");
//    private By iconThreeDots = By.xpath("//div[contains(@onclick,'add_new_address')]//preceding-sibling::div[1]//button");

    private By contentAddressAddedNew = By.xpath("//div[@onclick='add_new_address()']//preceding-sibling::div[1]/div//span[normalize-space()='Address:']/following-sibling::span");
    private By contentPostalcodeAddedNew = By.xpath("//div[@onclick='add_new_address()']//preceding-sibling::div[1]/div//span[normalize-space()='Postal code:']/following-sibling::span");
    private By contentCityAddedNew = By.xpath("//div[@onclick='add_new_address()']//preceding-sibling::div[1]/div//span[normalize-space()='City:']/following-sibling::span");
    private By contentStateAddedNew = By.xpath("//div[@onclick='add_new_address()']//preceding-sibling::div[1]/div//span[normalize-space()='State:']/following-sibling::span");
    private By contentCountryAddedNew = By.xpath("//div[@onclick='add_new_address()']//preceding-sibling::div[1]/div//span[normalize-space()='Country:']/following-sibling::span");
    private By contentPhoneAddedNew = By.xpath("//div[@onclick='add_new_address()']//preceding-sibling::div[1]/div//span[normalize-space()='Phone:']/following-sibling::span");


    //Initialize constructor

    public void addNewAddress() {
        waitForPageLoaded();
        clickElement(menuManageProfile);
        scrollToElementWithJS(titleAddress);
        verifyElementVisible(titleAddress, "Address block is NOT displayed");
        clickElement(buttonAddressNewAddress);
        verifyElementVisible(titlePopupNewAddress, "Popup Address is NOT displayed");

        setText(inputYourAddress, "Hà Nội");
        clickElement(selectCountry);
        setTextEnter(inputSearchCountry, "Vietnam");
        clickElement(selectState);
        setTextEnter(inputSearchState, "Hà Nội");
        clickElement(selectCity);
        setTextEnter(inputSearchCity, "Hà Nội");
        setText(inputPostalCode, "10000");
        setText(inputPhoneAddress, "0123456789");
        clickElement(buttonSaveNewAddress);
        sleep(1);

    }

    public void verifyNewAddressAdded() {
        waitForPageLoaded();
        scrollToElementWithJSBottom(buttonAddressNewAddress);
        sleep(1);
        verifyEquals(contentAddressAddedNew, "Hà Nội", "The address just added is not correct");
        verifyEquals(contentCountryAddedNew, "Vietnam", "The country just added is not correct");
        verifyEquals(contentStateAddedNew, "Hà Nội", "The State just added is not correct");
        verifyEquals(contentCityAddedNew, "Hà Nội", "The City just added is not correct");
        verifyEquals(contentPostalcodeAddedNew, "10000", "The PostalCode just added is not correct");
        verifyEquals(contentPhoneAddedNew, "0123456789", "The Phone Address just added is not correct");
    }

    public void updateBasicInfo() {
    }

    public void changeEmail() {
    }

}
