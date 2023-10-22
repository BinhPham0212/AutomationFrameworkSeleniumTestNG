package BinhAT.drivers;

import org.openqa.selenium.WebDriver;

import java.sql.Driver;

import static BinhAT.keywords.WebUI.sleep;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private DriverManager() {
        super();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driver) {
        DriverManager.driver.set(driver);
    }

    public static void quit() {
        DriverManager.driver.get().quit();
        driver.remove();
    }


}