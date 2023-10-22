package BinhAT.listeners;

import BinhAT.helpers.CaptureHelper;
import BinhAT.helpers.PropertiesHelper;
import BinhAT.reports.AllureManager;
import BinhAT.reports.ExtentReportManager;
import BinhAT.reports.ExtentTestManager;
import BinhAT.utils.LogUtils;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.swing.event.ChangeListener;

import static BinhAT.helpers.CaptureHelper.screenRecorder;
import static BinhAT.helpers.CaptureHelper.startRecord;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext result) {
        PropertiesHelper.loadAllFiles();
        //Khởi tạo report (Extent và Allure)
    }

    @Override
    public void onFinish(ITestContext result) {
        LogUtils.info("End testing " + result.getName() + "\n====================================");
        //Kết thúc và thực thi Extents Report
        ExtentReportManager.getExtentReports().flush();

    }

    @Override
    public void onTestStart(ITestResult result) {
        LogUtils.info("Running test case " + result.getName());
        //Record video
        startRecord(result.getName());

        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(result), getTestDescription(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.info("Test case " + result.getName() + " is passed.");
        //Stop record video and Capture Screenshot
        CaptureHelper.stopRecord();
        CaptureHelper.captureScreenshot(result.getName());
        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, result.getName() + " is passed.");

    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogUtils.error("Test case " + result.getName() + " is failed.");
        //Screenshot khi fail
        CaptureHelper.captureScreenshot("FAIL_" + result.getName());
        LogUtils.error(result.getThrowable().toString());

        //Stop record video
        CaptureHelper.stopRecord();
        //Extent Report
        ExtentTestManager.addScreenShot(result.getName());
        ExtentTestManager.logMessage(Status.FAIL, result.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, result.getName() + " is failed.");
        //Allure Report
        AllureManager.saveTextLog(result.getName() + " is failed.");
        AllureManager.saveScreenshotPNG();

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.error("Test case " + getTestDescription(result) + " is skipped.");
        LogUtils.error(result.getThrowable().toString());
        //Stop record video
        CaptureHelper.stopRecord();
        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, result.getThrowable().toString());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        //Screenshot khi fail a part
        CaptureHelper.captureScreenshot(result.getName());
        LogUtils.info(result.getThrowable().toString());

        //Stop record video
        CaptureHelper.stopRecord();
        //Extent Report
        ExtentTestManager.addScreenShot(result.getName());
        LogUtils.error("This Testcase is FAILED but that have a Success part: " + getTestDescription(result));
        LogUtils.error(result.getThrowable().toString());
    }
}
