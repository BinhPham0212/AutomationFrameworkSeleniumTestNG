package BinhAT.keywords;

import BinhAT.drivers.DriverManager;
import BinhAT.reports.AllureManager;
import BinhAT.reports.ExtentTestManager;
import BinhAT.utils.LogUtils;
import com.aventstack.extentreports.Status;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import BinhAT.utils.DateUtils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

import static BinhAT.drivers.DriverManager.*;
import static java.lang.Thread.sleep;

public class WebUI {
    /**
     * Hàm xây dựng để truyền Driver từ ngoài vào để có giá trị. Và k cần truyền vào cho từng hàm nữa ( Static WebgetDriver())
     * Khởi tạo driver toàn cục
     */
    private static int EXPLICIT_WAIT_TIMEOUT = 10;
    private static int WAIT_PAGE_LOADED_TIMEOUT = 30;

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }
    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }


    public static void logConsole(String message) {
        System.out.println(message);
    }

    @Step("Open URL: {0}")
    public static void openURL(String URL) {
        getDriver().get(URL);
        waitForPageLoaded();
        LogUtils.info("Open URL: " + URL);
        ExtentTestManager.logMessage(Status.PASS, "Open URL: " + URL);
    }

    /* Actions class
     */
    @Step("Get Current URL:")
    public static String getCurrentUrl() {
        waitForPageLoaded();
        LogUtils.info("Get Current URL: " + getDriver().getCurrentUrl());
        ExtentTestManager.logMessage(Status.PASS, "Get Current URL: " + getDriver().getCurrentUrl());

        return getDriver().getCurrentUrl();
    }

    @Step("Clicked on element {0}")
    public static void clickElement(By by) {
        waitForElementVisible(by);
        getWebElement(by).click();
        LogUtils.info("Clicked on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Clicked on element " + by);
    }

    @Step("Click on the element by Javascript {0}")
    public static void clickElementWithJs(By by) {
        waitForElementPresent(by);
        //Scroll to element với Javascript Executor
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        //Click with JS
        js.executeScript("arguments[0].click();", getWebElement(by));

        LogUtils.info("Click on element with JS: " + by);
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.logMessage(Status.PASS,"Click on element with JS: " + by);
        }
        AllureManager.saveTextLog("Click on element with JS: " + by);
    }

    @Step("Set text {1} on element {0}")
    public static void setText(By by, String value) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(value);
        LogUtils.info("Set text " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text " + value + " on element " + by);
    }
    @Step("Set text {1} on element {0}")
    public static void setText(By by, int value) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys("value");
        LogUtils.info("Set text " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text " + value + " on element " + by);
    }

    @Step("Set text {1} on element {0} and enter")
    public static void setTextEnter(By by, String value) {
        waitForElementVisible(by);
        getWebElement(by).sendKeys(value, Keys.ENTER);
        LogUtils.info("Set text " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Set text " + value + " on element " + by);
    }

    @Step("Cleared on element {0}")
    public static void clearInputElement(By by) {
        waitForElementVisible(by);
        getWebElement(by).clear();
        LogUtils.info("Cleared content with element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Cleared on element " + by);
    }

    @Step("Get text element {0}")
    public static String getTextELement(By by) {
        waitForElementVisible(by);
        LogUtils.info("Get text element " + by);
        LogUtils.info("==> Text: " + getWebElement(by).getText());
        ExtentTestManager.logMessage(Status.PASS, "Get text element " + by);
        ExtentTestManager.logMessage(Status.INFO, "==> Text: " + getWebElement(by).getText());
        return getWebElement(by).getText();
    }


    @Step("Get attribute {1} value of element {0}")
    public static String getAttributeELement(By by, String attributeName) {
        waitForElementVisible(by);
        LogUtils.info("Get attribute value of element " + by);
        LogUtils.info("==> Attribute value: " + getWebElement(by).getAttribute(attributeName));
        ExtentTestManager.logMessage(Status.PASS, "Get attribute value of element " + by);
        ExtentTestManager.logMessage(Status.INFO, "==> Attribute value: " + getWebElement(by).getAttribute(attributeName));
        return getWebElement(by).getAttribute(attributeName);
    }

    @Step("Hover on element {0}")
    public static void hoverOnElement(By by) {
        Actions action = new Actions(getDriver());
        action.moveToElement(getWebElement(by));
        LogUtils.info("Hover on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Hover on element" + by);
    }

    @Step("Right click on element {0}")
    public static void rightClickElement(By by) {
        waitForElementVisible(by);
        Actions action = new Actions(getDriver());
        action.contextClick(getWebElement(by));
        LogUtils.info("Right click on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Right click on element" + by);
    }

    @Step("Highlight get element {0}")
    public static WebElement highLightElement(By by) {
        waitForElementVisible(by);
        // Add color border of Element
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='5px solid blue'", getWebElement(by));
            sleep(3);
            LogUtils.info("Highlight on element " + by);
        }
        return getWebElement(by);
    }


//    @Step("Highlight get text element {0}")
//    public static WebElement highLightGetTextElement(By by) {
//        waitForElementVisible(by);
//        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
//        if (getDriver() instanceof JavascriptExecutor) {
//            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute('style', 'border:5px solid red; background:yellow'", getWebElement(by));
//            sleep(1);
//        }
//        return getWebElement(by);
//    }

    @Step("Scroll to element {0}")
    public static void scrollToElementWithJS(By by) {
        //JavascriptExecutor
        //Find Element trong DOM wait for Element trong DOM
        waitForElementPresent(by);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);", getWebElement(by));
        LogUtils.info("Scroll to element " + by);
        //Dùng action class
    }

    @Step("Scroll to element {0}")
    public static void scrollToElementWithJSBottom(By by) {
        //JavascriptExecutor
        //Find Element trong DOM wait for Element trong DOM
        waitForElementPresent(by);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(false);", getWebElement(by));
        LogUtils.info("Scroll to element " + by);
        //Dùng action class
    }

    /**
     * Upload local file with senkeys and Robot class
     */
    @Step("Upload File with open Local Form")
    public static void uploadFileWithLocalForm(By by, String filePath) {
//        String filePath = SystemsHelper.getCurrentDir() + "filePathUpload";

        Actions action = new Actions(DriverManager.getDriver());
        //Click để mở form upload
        action.moveToElement(getWebElement(by)).click().perform();
        sleep(1);

        // Khởi tạo Robot class
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Copy File path vào Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        sleep(1);

        //Copy anh paste path
        // Nhấn Control+V để dán
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        // Xác nhận Control V trên
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.delay(2000);
        // Nhấn Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.delay(3000);

        LogUtils.info("Upload File with Local Form: " + filePath);
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.logMessage("Upload File with Local Form: " + filePath);
        }
        AllureManager.saveTextLog("Upload File with Local Form: " + filePath);
    }

    @Step("Upload File with SendKeys")
    public static void uploadFileWithSendKeys(By by, String filePath) {
//        String filePath = SystemsHelper.getCurrentDir() + "filePathUpload";
        waitForElementVisible(by);
        getWebElement(by).sendKeys(filePath);


        LogUtils.info("Upload File with SendKeys");
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.logMessage("Upload File with SendKeys");
        }
        AllureManager.saveTextLog("Upload File with SendKeys");

    }


    /* Actions class====
     */

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //Check Element tồn tại trong DOM và hiển thị trên UI
    public static WebElement waitForElementVisible(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));

            boolean check = verifyElementVisible(by, second);
            if (check == true) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } else {
                scrollToElementWithJS(by);
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
        }
        return null;
    }

    public static WebElement waitForElementVisible(By by) {
        waitForElementPresent(by);

        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            boolean check = verifyElementVisible(by, 1);
            if (check == true) {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            } else {
                scrollToElementWithJS(by);
                return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            }
        } catch (Throwable error) {
            LogUtils.error("Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
        }
        return null;
    }

    //Check xem Element tồn tại
    public static void waitForElementPresent(By by, int second) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitForElementPresent(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT));

        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }



    public static void waitForElementClickable(By by, int second) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second));

        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    @Step("Verify element visible {0} with timeout {1} second")
    public static boolean verifyElementVisible(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            LogUtils.error("The element is not visible. " + e.getMessage());
            Assert.fail("The element is NOT visible. " + by);
            return false;
        }
        //truyền object , chờ đợi time nếu quá time báo cache. tìm thấy true (thoát khỏi vòng), false trả về hàm này
    }

    @Step("Verify element visible {0}")
    public static boolean verifyElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Verify element visible {0}")
    public static boolean verifyElementVisible(By by, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            LogUtils.info("Verify element visible " + by);
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element is not visible. " + by);
                Assert.fail("The element is NOT visible. " + by);
            } else {
                LogUtils.error(message + by);
                Assert.fail(message + by);
            }
            return false;
        }
    }

    public static boolean verifyElementNotVisible(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Step("Verify Equals: {0} ---AND--- {1}")
    public static boolean verifyEquals(By value1, String value2, String message) {
        String getValue1 = getTextELement(value1).trim();
        boolean result = getValue1.equals(value2);
        if (result == true) {
            LogUtils.info("Verify Equals: " + value1 + " = " + value2);
            if (ExtentTestManager.getTest() != null) {
                ExtentTestManager.logMessage(Status.PASS,"Verify Equals: " + value1 + " = " + value2);
            }
            AllureManager.saveTextLog("Verify Equals: " + value1 + " = " + value2);
        } else {
            LogUtils.info("Verify Equals: " + getValue1 + " != " + value2);
            if (ExtentTestManager.getTest() != null) {
                ExtentTestManager.logMessage(Status.FAIL,"Verify Equals: " + getValue1 + " NOT EQUALS " + value2);
            }
            AllureManager.saveTextLog("Verify Equals: " + getValue1 + " != " + value2);
            Assert.assertEquals(getValue1, value2, message);
        }
        return result;
    }
    public static boolean verifyEquals(Object value1, Object value2,String message) {
        boolean result = value1.equals(value2);
        if (result == true) {
            LogUtils.info("Verify Equals: " + value1 + " = " + value2);
            if (ExtentTestManager.getTest() != null) {
                ExtentTestManager.logMessage(Status.PASS,"Verify Equals: " + value1 + " = " + value2);
            }
            AllureManager.saveTextLog("Verify Equals: " + value1 + " = " + value2);
        } else {
            LogUtils.info("Verify Equals: " + value1 + " != " + value2);
            if (ExtentTestManager.getTest() != null) {
                ExtentTestManager.logMessage(Status.FAIL,"Verify Equals: " + value1 + " NOT EQUALS " + value2);
            }
            AllureManager.saveTextLog("Verify Equals: " + value1 + " != " + value2);
            Assert.assertEquals(value1, value2, message);
        }
        return result;
    }

    public static boolean verifyElementPresent(By by, String message) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify element present " + by);
            return true;
        } catch (Exception e) {
            if (message.isEmpty() || message == null) {
                LogUtils.error("The element does NOT present. " + e.getMessage());
                Assert.fail("The element does NOT present. " + e.getMessage());
            } else {
                LogUtils.error(message);
                Assert.fail(message);
            }

            return false;
        }
    }

    public static boolean verifyElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            LogUtils.info("Verify element present " + by);
            if (ExtentTestManager.getTest() != null) {
                ExtentTestManager.logMessage(Status.INFO,"Verify element present " + by);
            }
            AllureManager.saveTextLog("Verify element present " + by);
            AllureManager.saveScreenshotPNG();
            return true;
        } catch (Exception e) {
            LogUtils.info("The element does NOT present. " + e.getMessage());
            Assert.fail("The element does NOT present. " + e.getMessage());
            return false;
        }
    }

    public static boolean verifyElementNotPresent(By by, int second) {
        try {
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(second), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }

    public static Boolean checkElementExist(By by) {
        List<WebElement> listElement = getDriver().findElements(by);

        if (listElement.size() > 0) {
            System.out.println("Element " + by + " existing in DOM.");
            return true;
        } else {
            System.out.println("Element " + by + " NOT exist in DOM.");
            return false;
        }
    }

    public static Boolean checkElementExist(String xpath) {
        List<WebElement> listElement = getDriver().findElements(By.xpath(xpath));

        if (listElement.size() > 0) {
            System.out.println("Element " + xpath + " existing in DOM.");
            return true;
        } else {
            System.out.println("Element " + xpath + " NOT exist in DOM.");
            return false;
        }
    }

    @Step("Switch to Window: {0}")
    public static void switchToWindow(String title) {
        waitForPageLoaded();
        //Get original window
        String originalWindow = DriverManager.getDriver().getWindowHandle();

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(EXPLICIT_WAIT_TIMEOUT), Duration.ofMillis(500));
        //Wait for the new window or tab
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        //Loop through until we find a new window handle
        for (String windowHandle : DriverManager.getDriver().getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                DriverManager.getDriver().switchTo().window(windowHandle);
                if (DriverManager.getDriver().getTitle().equals(title)) {
                    break;
                }
            }
        }

    }

    /**
     * Wait for Page loaded
     * Chờ đợi trang tải xong (Javascript tải xong)
     */
    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(WAIT_PAGE_LOADED_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver)  //driver này là tên của tham số
            {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        //Check JS is Ready
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            System.out.println("Javascript is NOT Ready.");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("FAILED. Timeout waiting for page load.");
            }
        }
    }


}

