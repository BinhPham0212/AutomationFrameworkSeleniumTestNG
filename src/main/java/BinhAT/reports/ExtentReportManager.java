package BinhAT.reports;

import BinhAT.utils.DateUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import BinhAT.utils.DateUtils;

public class ExtentReportManager {
    private static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports getExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("reports/extentreport/extentreport_SuiteLoginTest " + DateUtils.getCurrentDateTimeCustom("_") + ".html");
        reporter.config().setReportName("Extent Report | Binh AT");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Framework Name", "Selenium Java | Binh AT");
        extentReports.setSystemInfo("Author", "Binh AT");
        return extentReports;
    }

}

