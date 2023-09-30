package BinhAT.pages;

import static BinhAT.keywords.WebUI.*;

import BinhAT.helpers.PropertiesHelper;
import BinhAT.pages.users.DashboardPage;
import BinhAT.reports.ExtentTestManager;
import BinhAT.utils.LogUtils;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;


public class LoginPage {

    private By popupEmailSubscribe = By.xpath("//h2[normalize-space()='Website Demo']/ancestor::div[contains(@class,'modal-dialog')]");
    private By skipPopupEmailSubscribe = By.xpath("//h2[normalize-space()='Website Demo']/parent::div//following-sibling::button[@data-value='removed']");
    private By buttonCookies = By.xpath("//button[normalize-space()='Ok. I Understood']");
    private By buttonLoginTopnavbar = By.xpath("//div[contains(@class,'top-navbar')]//a[normalize-space()='Login']");
    private By headerLoginPage = By.xpath("//h1[normalize-space()='Login to your account.']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By messageLoginInvalid = By.xpath("//span[normalize-space()='Invalid login credentials']");
    //    private By messageLoginSuccess = By.xpath("//span[normalize-space()='Cache cleared successfully']");
    private By messageRequiredEmail = By.xpath("//strong[contains(text(),'The email field is required when phone is not present.')]");
    private By messageRequiredPassword = By.xpath("//input[contains(@class, 'is-invalid') and @id = 'password']");

    //Viết hàm xử lý cho trang Login
    public void clickSkipPopupSubscribe() {
        if (verifyElementVisible(popupEmailSubscribe) == true) {
            clickElement(skipPopupEmailSubscribe);
            LogUtils.info("Click button remove popup email subscription");
        }
    }

    public void clickButtonCookies() {
        clickElement(buttonCookies);
        LogUtils.info("Click button cancel button cookies");
    }

    public void verifyHeaderLoginPage() {
        Assert.assertEquals(getTextELement(headerLoginPage), "Login to your account.", "FAIL. Header not match.");
        LogUtils.info("Header login page not match.");
        ExtentTestManager.logMessage(Status.FAIL, "Header is not match.");
    }

    public void verifyErrorMessageDisplays() {
        Assert.assertEquals(getTextELement(messageLoginInvalid), "Invalid login credentials", "FAIL. Error message Login not match.");
        LogUtils.info("Error message Login not match.");
        ExtentTestManager.logMessage(Status.FAIL, "Error message Login not match.");
    }

    public void verifyMessageRequiredEmail() {
        String expectedMessage = "The email field is required when phone is not present.";
        Assert.assertEquals(getTextELement(messageRequiredEmail), expectedMessage, "Mesage is not match");

    }

    public void enterEmail(String email) {
//        driver.findElement(inputEmail).sendKeys(email);
        setText(inputEmail, email);
    }

    public void enterPassword(String password) {
//        driver.findElement(inputPassword).sendKeys(password);
        setText(inputPassword, password);
    }

    public void clickOnLoginButton() {
        clickElement(buttonLogin);
    }

    public void clickOnLoginButtonTopnavbar() {
        clickElement(buttonLoginTopnavbar);
    }

    /**
     * page
     */
    public void openLoginPage() {
        openURL(PropertiesHelper.getValue("URL_CMS"));
        clickSkipPopupSubscribe();
        clickButtonCookies();
        clickOnLoginButtonTopnavbar();
        waitForPageLoaded();
        verifyElementVisible(headerLoginPage, "Login page is NOT displayed");
        verifyHeaderLoginPage();
    }

    public void loginFailWithEmailNull(String password) {
        openLoginPage();
        sleep(2);
        clearInputElement(inputEmail);
        setText(inputPassword,password);
        clickElement(buttonLogin);
        waitForPageLoaded();
        sleep(1);
        verifyMessageRequiredEmail();
    }

    public void loginFailWithInvalidEmail(String email, String password) {
        openLoginPage();
        sleep(2);
        clearInputElement(inputEmail);
        setText(inputEmail,email);
        setText(inputPassword,password);
        clickElement(buttonLogin);
        waitForPageLoaded();
        sleep(1);
        verifyElementVisible(messageLoginInvalid,"Email is incorrect. Login FAILED");
    }

    public void loginFailWithInvalidPassword(String email, String password) {
        openLoginPage();
        sleep(2);
        setText(inputEmail,email);
        clearInputElement(inputPassword);
        setText(inputPassword,password);
        clickElement(buttonLogin);
        waitForPageLoaded();
        sleep(1);
        verifyElementVisible(messageLoginInvalid,"Password is incorrect.Login FAILED");
        verifyErrorMessageDisplays();
    }

    public void loginFailWithPasswordNull(String email) {
        openLoginPage();
        sleep(2);
        setText(inputEmail,email);
        clearInputElement(inputPassword);
        clickElement(buttonLogin);
        waitForPageLoaded();
        sleep(1);
        verifyElementPresent(messageRequiredPassword,"No warning password input");
    }

    public void loginSuccessWithCustomerAccount(String email, String password) {
        openLoginPage();
        sleep(2);
        clearInputElement(inputEmail);
        setText(inputEmail, email);
        clearInputElement(inputPassword);
        setText(inputPassword, password);
        clickElement(buttonLogin);
        waitForPageLoaded();
        sleep(1);
        waitForElementVisible(new DashboardPage().titleDashboard);
        verifyElementVisible(new DashboardPage().titleDashboard, "Dashboard page is NOT displayed.");
    }

}
