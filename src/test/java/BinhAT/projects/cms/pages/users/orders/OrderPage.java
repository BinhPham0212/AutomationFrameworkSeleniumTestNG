package BinhAT.projects.cms.pages.users.orders;

import BinhAT.helpers.PropertiesHelper;
import BinhAT.projects.cms.pages.users.DashboardPage;
import BinhAT.projects.cms.pages.users.products.ProductInfoPage;
import BinhAT.projects.cms.pages.users.profilepage.ManageProfilePage;
import BinhAT.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static BinhAT.keywords.WebUI.*;

public class OrderPage {
    // Initial Object class
    DashboardPage dashboardPage = new DashboardPage();
    ProductInfoPage productInfoPage = new ProductInfoPage();
    ManageProfilePage manageProfilePage = new ManageProfilePage();

    /* Object Element
     */
    public By buttonAddToCart = By.xpath("//button[@onclick='addToCart()']");
    private By messageItemAdded = By.xpath("//h3[normalize-space()='Item added to your cart!']");
    private By buttonClosePopup = By.xpath("//div[@id='addToCart']//button[contains(@class,'close')]");
    private By popupAddToCartSuccessed = By.xpath("//h3[normalize-space()='Item added to your cart!']");
    public By buttonBackToShopping = By.xpath("//button[normalize-space()='Back to shopping']");
    private By closepopupAddToCartSuccessed = By.xpath("//button[@aria-label='Close']");
    public By buttonProceedToCheckout = By.xpath("//a[normalize-space()='Proceed to Checkout']");

    private By labelTotalPrice = By.xpath("//strong[@id='chosen_price']");
    private By buttonToShiping = By.xpath("//a[normalize-space()='Continue to Shipping']");
    private By divNewAddedAddress = By.xpath("//div[contains(@onclick,'add_new_address')]/parent::div//preceding-sibling::div[1]");
    private By buttonToDeliveryInfo = By.xpath("//button[normalize-space()='Continue to Delivery Info']");
    private By buttonToPayment = By.xpath("//button[normalize-space()='Continue to Payment']");
    private By checkboxAgreeTermAndConditions = By.xpath("//input[@id='agree_checkbox']/parent::label");
    private By buttonSubmitOrder = By.xpath("//button[normalize-space()='Complete Order']");
    private By messageOrderSuccess = By.xpath("//h1[normalize-space()='Thank You for Your Order!']");
    private By labelOrderCodeSuccess = By.xpath("//*[contains(text(), 'Order Code:')]/span");
    private By subTotal = By.xpath("//th[normalize-space()='Subtotal']//following-sibling::td//span");
    private By buttonPlusQuantity = By.xpath("//button[@data-type='plus']");
    private By lablelCodePurchase = By.xpath("(//td[@class='footable-first-visible'])[1]");
    private By lablelOrderCodeID = By.xpath("//*[contains(text(), 'Order Code')]//following-sibling::td");
    private By listCartItem = By.xpath("//div[@id='cart_items']//li[contains(@class,'list-group-item')]");
    private By iconRemoveFromCart = By.xpath("//button[contains(@onclick,'removeFromCart')]");
    /* Action class
     */

    public static int sumPrice = 0;   //Total price product add to card
    public void addToCart(String nameProduct, int quantityProduct) {
        waitForPageLoaded();
        setText(dashboardPage.inputSearchProduct, PropertiesHelper.getValue(nameProduct));
        sleep(5);
        clickElement(By.xpath("//div[@id='search-content']//div[contains(text(),'" + PropertiesHelper.getValue(nameProduct) + "')]"));
        for (int i = 1; i < quantityProduct; i++) {
            clickElement(buttonPlusQuantity);
        }
        waitForPageLoaded();
        /* Verify Total Price = Price Product * quantity
         */
        String getpriceProduct = getTextELement(productInfoPage.productPriceDiscount).trim();
        String quantities = getAttributeELement(productInfoPage.quantityProduct, "value").trim();
        int sumPriceProduct = (Integer.parseInt(getpriceProduct.replace("$", "").replace(",", "").split("\\.")[0])) * Integer.parseInt(quantities);
        int TotalPrice = Integer.parseInt(getTextELement(labelTotalPrice).replace("$", "").replace(",", "").split("\\.")[0]);
        verifyEquals(sumPriceProduct, TotalPrice, "Total Price is NOT equal to Sum Price");

        List<Integer> priceList = new ArrayList<Integer>();
        priceList.clear();
        priceList.add(TotalPrice);
        for (int price : priceList) {
            sumPrice += price;
            LogUtils.info("Total price of product added to cart " + sumPrice);
        }
        /*
         */

        scrollToElementWithJSBottom(buttonAddToCart);
        clickElement(buttonAddToCart);
        sleep(2);
        verifyElementVisible(popupAddToCartSuccessed, "Product has not been added to cart");
    }

    public void clearCartItems() {
        if (checkElementExist(listCartItem) == true) {
            List<WebElement> removeCartItem = getWebElements(listCartItem);
            for (int i = 0; i < removeCartItem.size(); i++) {
                clickElement(dashboardPage.headerCardItem);
                clickElement(iconRemoveFromCart);
                sleep(2);
                if (removeCartItem.size() == 0) {
                    break;
                }
            }
        }
    }
    public static String OrderCodeSucess;
    public void checkoutOrder() {
        waitForPageLoaded();
        clickElement(buttonToShiping);
        waitForPageLoaded();
        clickElement(divNewAddedAddress);
        waitForPageLoaded();
        clickElement(buttonToDeliveryInfo);
        clickElement(buttonToPayment);
        clickElement(checkboxAgreeTermAndConditions);
        sleep(3);

        int subTotalBill = Integer.parseInt(getTextELement(subTotal).replace("$", "").replace(",", "").split("\\.")[0]);
        LogUtils.info("Sub Total Bill: " + subTotalBill);
        LogUtils.info("Sum Price: " + sumPrice);
        verifyEquals(sumPrice, subTotalBill, "Sub Total Bill is NOT EQUAL to total price of product added to cart");

        clickElement(buttonSubmitOrder);
        verifyElementVisible(messageOrderSuccess, "Order is FAILED");
        OrderCodeSucess = getTextELement(labelOrderCodeSuccess);
        LogUtils.info(getTextELement(labelOrderCodeSuccess));
    }

    public void verifyCodePurchase() {
        clickElement(dashboardPage.buttonMyPanel);
        waitForPageLoaded();
        clickElement(manageProfilePage.menuPurchaseHistory);
        waitForPageLoaded();
        sleep(1);
        waitForElementVisible(lablelCodePurchase);
        clickElement(lablelCodePurchase);
        waitForPageLoaded();
        verifyEquals(lablelOrderCodeID,OrderCodeSucess, "Code Purchase is NOT equal to Order Code ID in Purchase History page");
    }
}
