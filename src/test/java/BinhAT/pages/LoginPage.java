package BinhAT.pages;

import static BinhAT.keywords.WebUI.*;

import BinhAT.helpers.PropertiesHelper;
import BinhAT.utils.LogUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;


public class LoginPage {

    private String PAGETEXT = "Login";

    //Lưu Object của trang Login
    //Dùng đối tượng By trong Selenium để khai báo tên Object cùng giá trị locator tương ứng
    private By  popupEmailSubscribe = By.xpath("//h2[normalize-space()='Website Demo']/ancestor::div[contains(@class,'modal-dialog')]");
    private By skipPopupEmailSubscribe = By.xpath("//h2[normalize-space()='Website Demo']/parent::div//following-sibling::button[@data-value='removed']");
     private By buttonCookies = By.xpath("//button[normalize-space()='Ok. I Understood']");
    private By buttonLoginTopnavbar = By.xpath("//div[contains(@class,'top-navbar')]//a[normalize-space()='Login']");
    private By headerLoginPage = By.xpath("//h1[normalize-space()='Login to your account.']");
    private By inputEmail = By.xpath("//input[@id='email']");
    private By inputPassword = By.xpath("//input[@id='password']");
    private By buttonLogin = By.xpath("//button[normalize-space()='Login']");
    private By messageLoginInvalid = By.xpath("//span[normalize-space()='Invalid login credentials']");
//    private By messageLoginSuccess = By.xpath("//span[normalize-space()='Cache cleared successfully']");



    //Viết hàm xử lý cho trang Login
    public void clickSkipPopupSubscribe() {
        if(verifyElementVisible(popupEmailSubscribe) == true) {
            clickElement(skipPopupEmailSubscribe);
            LogUtils.info("Click button remove popup email subscription");
        }
    }

    public void clickButtonCookies() {
        clickElement(buttonCookies);
        LogUtils.info("Click button cancel button cookies");
    }

    public void verifyHeaderLoginPage() {
        Assert.assertEquals(getTextELement(headerLoginPage), "Login to your account.", "FAIL. Header not match. Login page is NOT displayed");
    }

    public void verifyErrorMessageDisplays() {
        Assert.assertTrue(getWebElement(messageLoginInvalid).isDisplayed(),"FAIL. Error message not displayed");
        Assert.assertEquals(getTextELement(messageLoginInvalid),"Invalid login credentials", "FAIL. Message error not match." );
    }

    public void enterEmail(String email) {
//        driver.findElement(inputEmail).sendKeys(email);
        setText(inputEmail,email);
    }

    public void enterPassword(String password) {
//        driver.findElement(inputPassword).sendKeys(password);
        setText(inputPassword,password);
    }

    public void clickOnLoginButton() {
        clickElement(buttonLogin);
    }
    public void clickOnLoginButtonTopnavbar() {
        clickElement(buttonLoginTopnavbar);
    }
    /**page
     * */
    public void openLoginPage() {
        openURL(PropertiesHelper.getValue("urlcms"));
        clickSkipPopupSubscribe();
        clickButtonCookies();
        clickOnLoginButtonTopnavbar();
        waitForPageLoaded();
        Assert.assertTrue(verifyElementVisible(headerLoginPage),"Login page is not displayed");
//        verifyElementVisible(headerLoginPage, "Login page is not displayed");
        verifyHeaderLoginPage();

    }

    public void loginInvalidEmail(String email, String password) {
        openURL(PropertiesHelper.getValue("url"));
        verifyHeaderLoginPage();
        enterEmail(email);
        enterPassword(password);
        clickOnLoginButton();
        //Verify message error is displayed correctly
        verifyErrorMessageDisplays();
    }
    public void loginInvalidPassword(String email, String password) {
        openURL(PropertiesHelper.getValue("url"));
    }


}
