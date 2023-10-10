package BinhAT.pages.users.orders;

import BinhAT.helpers.ExcelHelper;
import BinhAT.helpers.PropertiesHelper;
import BinhAT.pages.users.DashboardPage;
import BinhAT.pages.users.products.ProductInfoPage;
import BinhAT.utils.LogUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Optional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static BinhAT.keywords.WebUI.*;

public class OrderPage {
    // Initial Object class
    DashboardPage dashboardPage = new DashboardPage();
    ProductInfoPage productInfoPage = new ProductInfoPage();

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
    /* Action class
     */
    public static int sumPrice = 0;
    public void addToCart(String nameProduct, @Optional("1") int quantityProduct) {
        waitForPageLoaded();
        setText(dashboardPage.inputSearchProduct, PropertiesHelper.getValue(nameProduct));
        sleep(5);
        clickElement(By.xpath("//div[@id='search-content']//div[contains(text(),'" + PropertiesHelper.getValue(nameProduct) + "')]"));
        for (int i = 1; i <= quantityProduct; i++) {
            clickElement(buttonPlusQuantity);
        }
        waitForPageLoaded();
        /* Verify Total Price = Price Product * quantity
         */
        String getpriceProduct = getTextELement(productInfoPage.productPriceDiscount).trim();
        String quantities = getAttributeELement(productInfoPage.quantityProduct, "value").trim();
        int sumPriceProduct = (Integer.parseInt(getpriceProduct.replace("$", "").replace(",", "").split("\\.")[0])) * Integer.parseInt(quantities);
        int TotalPrice = Integer.parseInt(getTextELement(labelTotalPrice).replace("$", "").replace(",", "").split("\\.")[0]);
        verifyEquals(sumPriceProduct,TotalPrice,"The total price is INCORRECTLY");

        List<Integer> priceList = new ArrayList<Integer>();
        priceList.clear();
        priceList.add(TotalPrice);
        for(int price : priceList){
            sumPrice += price;
            LogUtils.info("Total price of product added to cart " + sumPrice);
        }

        scrollToElementWithJSBottom(buttonAddToCart);
        clickElement(buttonAddToCart);
        sleep(2);
        verifyElementVisible(popupAddToCartSuccessed,"Product has not been added to cart");
    }

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
        LogUtils.info("Sub Total Bill: "+ subTotalBill);
        LogUtils.info("Sum Price: " +sumPrice);
        verifyEquals(sumPrice,subTotalBill, "SubTotal is not equal Sum Price");

        clickElement(buttonSubmitOrder);
        verifyElementVisible(messageOrderSuccess, "Order is FAILED");
        LogUtils.info(getTextELement(labelOrderCodeSuccess));

    }

    public void verifyCodePurchase() {

    }


}
