package BinhAT.common;

import BinhAT.drivers.DriverManager;
import BinhAT.helpers.CaptureHelper;
import BinhAT.helpers.PropertiesHelper;
import BinhAT.listeners.TestListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
@Listeners(TestListener.class)
public class BaseTest {
    @BeforeMethod
    @Parameters({"browser"})
    public static void createDriver(@Optional("chrome") String browser) {
//        System.setProperty("webdriver.http.factory", "jdk-http-client"); //Fix warning Connection reset
        WebDriver driver = setupDriver(browser);
        DriverManager.setDriver(driver);   //Set giá trị driver đã được khởi tạo vào ThreadLocal
    }

    public static WebDriver setupDriver(String browserName) {
        WebDriver driver;   // Biến cục bộ để lưu tạm mỗi lần khởi tạo browser
        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }

    private static WebDriver initChromeDriver() {
        System.out.println("Launching Chrome browser...");
        WebDriverManager.chromedriver().setup();
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        return driver;
    }

    private static WebDriver initEdgeDriver() {
        System.out.println("Launching Edge browser...");
        WebDriverManager.edgedriver().setup();
        System.setProperty("webdriver.gecko.driver","C:\\microsoftedge\\msedgedriver.exe");
        WebDriver driver;
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        return driver;
    }

    private static WebDriver initFirefoxDriver() {
        System.out.println("Launching Firefox browser...");
        WebDriverManager.firefoxdriver().setup();
        System.setProperty("webdriver.gecko.driver","C:\\firefoxdriver\\geckodriver.exe");
        WebDriver driver;
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        return driver;
    }

    @AfterMethod
//    public static void closeDriver(ITestResult iTestResult) {
    public static void closeDriver() {
//        if(iTestResult.getStatus() == ITestResult.FAILURE) {
//            //Take screenshot
//            // Tạo tham chiếu của TakesScreenshot
//            CaptureHelper.captureScreenshot(iTestResult.getName());
//        }
//        CaptureHelper.stopRecord();

        if(DriverManager.getDriver() != null) {
            DriverManager.quit();    //Đóng browser và xóa luôn thread
        }
    }

}