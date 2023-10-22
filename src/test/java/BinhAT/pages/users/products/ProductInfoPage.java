package BinhAT.pages.users.products;

import BinhAT.pages.users.DashboardPage;
import org.openqa.selenium.By;
import static BinhAT.keywords.WebUI.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductInfoPage {
    private By productName = By.xpath("//h1");
    public By productPriceDiscount = By.xpath("//div[normalize-space()='Discount Price:']/parent::div//following-sibling::div//strong");
    private By productCost = By.xpath("//div[normalize-space()='Discount Price:']/parent::div//following-sibling::div//strong");
    public By quantityProduct = By.xpath("//input[@name='quantity']");
    private By productUnit = By.xpath("//div[normalize-space()='Discount Price:']/parent::div//following-sibling::div//strong//following-sibling::span");
    private By productDescription = By.xpath("//div[@id='tab_default_1']//p");

    private By selectProductName = By.xpath("(//div[contains(@class,'product-name')])[1]");

    /* Initial Object class
    */
    DashboardPage dashboardPage = new DashboardPage();

    public ArrayList productInfo(String nameProduct) {
        waitForPageLoaded();
        setText(dashboardPage.inputSearchProduct,nameProduct);
        sleep(5);       //wait for Load Product
        clickElement(selectProductName);
        waitForPageLoaded();
        sleep(1);

        String name = getTextELement(By.xpath("//h1[contains(.,'" + nameProduct + "')]"));
        String cost = getTextELement(productCost);
        String price = getTextELement(productPriceDiscount);
        String unit = getTextELement(productUnit).substring(1);
        String description = getTextELement(productDescription);
        List<String> arrayList = new ArrayList<String>();
        arrayList.add(name);
        arrayList.add(cost);
        arrayList.add(price);
        arrayList.add(unit);
        arrayList.add(description);
        System.out.println("ArrayList: " + arrayList);
        System.out.println("Total ArrayList: " + arrayList.size());

        return (ArrayList) arrayList;
    }


}
