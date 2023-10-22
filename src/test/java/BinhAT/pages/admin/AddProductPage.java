package BinhAT.pages.admin;

import BinhAT.helpers.SystemsHelper;
import BinhAT.pages.LoginPage;
import BinhAT.pages.users.products.ProductInfoPage;
import org.openqa.selenium.By;

import static BinhAT.keywords.WebUI.*;

public class AddProductPage {
    LoginPage loginPage = new LoginPage();
    ProductInfoPage productInfoPage = new ProductInfoPage();

    private By menubarProduct = By.xpath("//span[normalize-space()='Products']");
    private By submenuAddNewProduct = By.xpath("//span[normalize-space()='Add New Product']");
    private By submenuAllProduct = By.xpath("//span[normalize-space()='All products']");
    private By titleAddNewProduct = By.xpath("//h5[normalize-space()='Add New Product']");
    private By labelProductName = By.xpath("//input[@placeholder='Product Name']");
    private By dropdownCategory = By.xpath("//button[@data-id='category_id']");
    private By inputSearchCategory = By.xpath("//div[@id='category']//input[@aria-label='Search']");
    private By inputUnit = By.xpath("//input[@name='unit']");
    private By inputTags = By.xpath("//tags[@role='tagslist']//span");
    private By chooseGalleryImage = By.xpath("//label[contains(normalize-space(),'Gallery Images')]/following-sibling::div//div[contains(@data-toggle,'uploader')]");
    private By buttonUploadNewTab = By.xpath("//a[normalize-space()='Upload New']");
    private By linkBrowse = By.xpath("//button[normalize-space()='Browse']");
//    private By linkBrowse = By.xpath("//input[@class = 'uppy-Dashboard-input']");
    private By buttonSelectFileTab = By.xpath("//a[normalize-space()='Select File']");
    private By inputSearchImg = By.xpath("//input[@name='aiz-uploader-search']");
    private By selectImageSearch = By.xpath("(//img[@class='img-fit'])[1]");
    private By buttonAddFiles = By.xpath("//button[normalize-space()='Add Files']");
    private By chooseThumbnailImage = By.xpath("//label[contains(normalize-space(),'Thumbnail Image')]/following-sibling::div//div[contains(@data-toggle,'uploader')]");
    private By inputUnitPrice = By.xpath("//input[@name='unit_price']");

    private By selectDate = By.xpath("//input[@placeholder='Select Date']");
    private By buttonSelectDiscountDate = By.xpath("//button[contains(@class,'applyBtn')]");
    private By inputDiscountPrice = By.xpath("//input[@name='discount']");
    private By inputQuantity = By.xpath("//input[@name='current_stock']");
    private By buttonSavePublish = By.xpath("//button[normalize-space()='Save & Publish']");
    private By messageAddProductSuccess = By.xpath("//span[normalize-space()='Product has been inserted successfully']");
    //Void verify Product
    private By inputSearchProduct = By.xpath("//input[@id='search']");
    private By buttonViewProduct = By.xpath("(//i[@class='las la-eye'])[1]");
    private By unitProduct = By.xpath("//div[normalize-space()='Price:']//following-sibling::div//span");
    private By contentDescription = By.xpath("//div[@id='tab_default_1']");
    private By blockDescription = By.xpath("//div[@role='textbox']");


    public void addNewProduct(String nameProduct, String nameCategory, String unitProduct,
                              String nameTags, String imageName, String unitPrice,String discountDate,
                              String discountPrice, String quantity, String description) {
        waitForPageLoaded();
        clickElement(menubarProduct);
        waitForPageLoaded();
        clickElement(submenuAddNewProduct);
        waitForPageLoaded();
        verifyElementVisible(titleAddNewProduct, "Add new product page is NOT displayed");
        //Add Product information
        setText(labelProductName, nameProduct);
        clickElement(dropdownCategory);
        setTextEnter(inputSearchCategory, nameCategory);
        setText(inputUnit, unitProduct);
        clickElement(inputTags);
        setTextEnter(inputTags, "nameTags");
        //Add product image
        sleep(2);
        clickElement(chooseGalleryImage);
        clickElement(buttonUploadNewTab);
        String filePath = SystemsHelper.getCurrentDir() + "src\\test\\Resources\\datatest\\"+imageName;
        uploadFileWithLocalForm(linkBrowse, filePath);
        clickElement(buttonSelectFileTab);
        String imgName = SystemsHelper.splitString(imageName,"[.]",0);
        setTextEnter(inputSearchImg,imgName);
        sleep(2);
        clickElement(selectImageSearch);
        clickElement(buttonAddFiles);
        clickElement(chooseThumbnailImage);
        setTextEnter(inputSearchImg,imgName);
        sleep(3);
        clickElementWithJs(selectImageSearch);
        clickElement(buttonAddFiles);
        //Add Product price + stock
        clearInputElement(inputUnitPrice);
        setText(inputUnitPrice,String.valueOf(unitPrice));
        clickElement(selectDate);
        setText(selectDate,discountDate);
        clickElement(buttonSelectDiscountDate);
        setText(inputDiscountPrice,discountPrice);
        setText(inputQuantity, quantity);
        //Add Meta Tags
        scrollToElementWithJSBottom(blockDescription);
        setText(blockDescription,description);

        //Click Save & Publish
        waitForPageLoaded();
        scrollToElementWithJSBottom(buttonSavePublish);
        clickElement(buttonSavePublish);
        verifyElementPresent(messageAddProductSuccess, "New Product is NOT added");
    }

    public void verifyNewProductAddedSuccess(String nameProduct,String unit,String description) {
        waitForPageLoaded();
        clickElement(submenuAllProduct);
        waitForPageLoaded();
        setTextEnter(inputSearchProduct,nameProduct);
        sleep(2);
        waitForPageLoaded();
        By nameProductSearch = By.xpath("(//td[@class='footable-first-visible'])[1]//following-sibling::td//span[contains(text(),'" + nameProduct + "')]");
        verifyElementVisible(nameProductSearch);
        clickElement(buttonViewProduct);
        switchToWindow(nameProduct);
        loginPage.clickSkipPopupSubscribe();
        waitForPageLoaded();
        sleep(2);
        verifyEquals(getTextELement(By.xpath("//h1[normalize-space()='" + nameProduct + "']")).trim(),nameProduct,"Product name title is NOT displayed correctly");
        String getUnit = SystemsHelper.splitString(getTextELement(unitProduct).trim(),"/",1);
        verifyEquals(getUnit, unit, "Unit is displayed NOT correctly");
        scrollToElementWithJSBottom(contentDescription);
        verifyEquals(getTextELement(contentDescription).trim(), description, "Description displayed wrong");

    }
}
