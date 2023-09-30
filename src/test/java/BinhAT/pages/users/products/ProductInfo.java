package BinhAT.pages.users.products;

import org.openqa.selenium.By;

public class ProductInfo {
    private By productName = By.xpath("//h1");
    private By productPriceDicount = By.xpath("//div[normalize-space()='Discount Price:']/parent::div//following-sibling::div//strong");
    private By productCost = By.xpath("//div[normalize-space()='Discount Price:']/parent::div//following-sibling::div//strong");
    private By productUnit = By.xpath("//div[normalize-space()='Discount Price:']/parent::div//following-sibling::div//strong//following-sibling::span");
    private By productDescription = By.xpath("//a[normalize-space()='Description']");



}
