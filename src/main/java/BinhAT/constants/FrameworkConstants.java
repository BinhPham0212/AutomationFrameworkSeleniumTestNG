package BinhAT.constants;

import BinhAT.helpers.PropertiesHelper;
import BinhAT.helpers.SystemsHelper;
import BinhAT.utils.ReportUtils;

import java.io.File;

public class FrameworkConstants {
    private FrameworkConstants() {
    }

    static {
        PropertiesHelper.loadAllFiles();
        //System.out.println("Data From FrameworkConstants: " + PropertiesHelper.getProperties());
    }

    public static final String PROJECT_PATH = SystemsHelper.getCurrentDir();
    public static final String EXCEL_DATA_FILE_PATH = PropertiesHelper.getValue("EXCEL_DATA_FILE_PATH");
    public static final String JSON_DATA_FILE_PATH = PropertiesHelper.getValue("JSON_DATA_FILE_PATH");
    public static final String EXCEL_CMS_LOGIN = PropertiesHelper.getValue("EXCEL_CMS_LOGIN");
    public static final String EXCEL_CMS_DATA = PropertiesHelper.getValue("EXCEL_CMS_DATA");
    public static final String EXCEL_CMS_PRODUCTS_USER = PropertiesHelper.getValue("EXCEL_CMS_PRODUCTS_USER");

    public static final String BROWSER = PropertiesHelper.getValue("BROWSER");
    public static final String URL_CRM = PropertiesHelper.getValue("URL_CRM");
    public static final String URL_CMS_ADMIN = PropertiesHelper.getValue("URL_CMS_ADMIN");
    public static final String URL_CMS_USER = PropertiesHelper.getValue("URL_CMS_USER");
    public static final String REMOTE_URL = PropertiesHelper.getValue("REMOTE_URL");
    public static final String REMOTE_PORT = PropertiesHelper.getValue("REMOTE_PORT");
    public static final String PROJECT_NAME = PropertiesHelper.getValue("PROJECT_NAME");
    public static final String REPORT_TITLE = PropertiesHelper.getValue("REPORT_TITLE");
    public static final String EXTENT_REPORT_NAME = PropertiesHelper.getValue("EXTENT_REPORT_NAME");
    public static final String EXTENT_REPORT_FOLDER = PropertiesHelper.getValue("EXTENT_REPORT_FOLDER");
    public static final String EXPORT_VIDEO_PATH = PropertiesHelper.getValue("EXPORT_VIDEO_PATH");
    public static final String EXPORT_CAPTURE_PATH = PropertiesHelper.getValue("EXPORT_CAPTURE_PATH");
    public static final String SEND_REPORT_TO_TELEGRAM = PropertiesHelper.getValue("SEND_REPORT_TO_TELEGRAM");
    public static final String TELEGRAM_TOKEN = PropertiesHelper.getValue("TELEGRAM_TOKEN");
    public static final String TELEGRAM_CHATID = PropertiesHelper.getValue("TELEGRAM_CHATID");
    public static final String AUTHOR = PropertiesHelper.getValue("AUTHOR");
    public static final String TARGET = PropertiesHelper.getValue("TARGET");
    public static final String HEADLESS = PropertiesHelper.getValue("HEADLESS");
    public static final String OVERRIDE_REPORTS = PropertiesHelper.getValue("OVERRIDE_REPORTS");
    public static final String OPEN_REPORTS_AFTER_EXECUTION = PropertiesHelper.getValue("OPEN_REPORTS_AFTER_EXECUTION");
    public static final String SEND_EMAIL_TO_USERS = PropertiesHelper.getValue("SEND_EMAIL_TO_USERS");
    public static final String SCREENSHOT_PASSED_STEPS = PropertiesHelper.getValue("SCREENSHOT_PASSED_STEPS");
    public static final String SCREENSHOT_FAILED_STEPS = PropertiesHelper.getValue("SCREENSHOT_FAILED_STEPS");
    public static final String SCREENSHOT_SKIPPED_STEPS = PropertiesHelper.getValue("SCREENSHOT_SKIPPED_STEPS");
    public static final String SCREENSHOT_ALL_STEPS = PropertiesHelper.getValue("SCREENSHOT_ALL_STEPS");
    public static final String ZIP_FOLDER = PropertiesHelper.getValue("ZIP_FOLDER");
    public static final String ZIP_FOLDER_PATH = PropertiesHelper.getValue("ZIP_FOLDER_PATH");
    public static final String ZIP_FOLDER_NAME = PropertiesHelper.getValue("ZIP_FOLDER_NAME");
    public static final String VIDEO_RECORD = PropertiesHelper.getValue("VIDEO_RECORD");

    public static final String LOCATE = PropertiesHelper.getValue("LOCATE");
    public static final String RETRY_TEST_FAIL = PropertiesHelper.getValue("RETRY_TEST_FAIL");

    public static final int WAIT_DEFAULT = Integer.parseInt(PropertiesHelper.getValue("WAIT_DEFAULT"));
    public static final int WAIT_IMPLICIT = Integer.parseInt(PropertiesHelper.getValue("WAIT_IMPLICIT"));
    public static final int WAIT_EXPLICIT = Integer.parseInt(PropertiesHelper.getValue("WAIT_EXPLICIT"));
    public static final int WAIT_PAGE_LOADED = Integer.parseInt(PropertiesHelper.getValue("WAIT_PAGE_LOADED"));
    public static final int WAIT_SLEEP_STEP = Integer.parseInt(PropertiesHelper.getValue("WAIT_SLEEP_STEP"));
    public static final String ACTIVE_PAGE_LOADED = PropertiesHelper.getValue("ACTIVE_PAGE_LOADED");

    public static final String EXTENT_REPORT_FOLDER_PATH = PROJECT_PATH + EXTENT_REPORT_FOLDER;
    public static final String EXTENT_REPORT_FILE_NAME = EXTENT_REPORT_NAME + ".html";
    public static String EXTENT_REPORT_FILE_PATH = EXTENT_REPORT_FOLDER_PATH + File.separator + EXTENT_REPORT_FILE_NAME;

    //Zip file for Report folder
    public static final String ZIPPED_EXTENT_REPORTS_FOLDER = EXTENT_REPORT_FOLDER + ".zip";

    public static final String YES = "yes";
    public static final String NO = "no";

    public static final String BOLD_START = "<b>";
    public static final String BOLD_END = "</b>";

    /* ICONS - START */

    public static final String ICON_SMILEY_PASS = "<i class='fa fa-smile-o' style='font-size:24px'></i>";
    public static final String ICON_SMILEY_SKIP = "<i class=\"fas fa-frown-open\"></i>";
    public static final String ICON_SMILEY_FAIL = "<i class='fa fa-frown-o' style='font-size:24px'></i>";

    public static final String ICON_OS_WINDOWS = "<i class='fa fa-windows' ></i>";
    public static final String ICON_OS_MAC = "<i class='fa fa-apple' ></i>";
    public static final String ICON_OS_LINUX = "<i class='fa fa-linux' ></i>";

    public static final String ICON_BROWSER_OPERA = "<i class=\"fa fa-opera\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_EDGE = "<i class=\"fa fa-edge\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_CHROME = "<i class=\"fa fa-chrome\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_FIREFOX = "<i class=\"fa fa-firefox\" aria-hidden=\"true\"></i>";
    public static final String ICON_BROWSER_SAFARI = "<i class=\"fa fa-safari\" aria-hidden=\"true\"></i>";

    public static final String ICON_Navigate_Right = "<i class='fa fa-arrow-circle-right' ></i>";
    public static final String ICON_LAPTOP = "<i class='fa fa-laptop' style='font-size:18px'></i>";
    public static final String ICON_BUG = "<i class='fa fa-bug' ></i>";
    /* style="text-align:center;" */

    public static final String ICON_SOCIAL_GITHUB_PAGE_URL = "https://anhtester.com/";
    public static final String ICON_SOCIAL_LINKEDIN_URL = "https://www.linkedin.com/in/anhtester/";
    public static final String ICON_SOCIAL_GITHUB_URL = "https://github.com/anhtester";
    public static final String ICON_SOCIAL_LINKEDIN = "<a href='" + ICON_SOCIAL_LINKEDIN_URL
            + "'><i class='fa fa-linkedin-square' style='font-size:24px'></i></a>";
    public static final String ICON_SOCIAL_GITHUB = "<a href='" + ICON_SOCIAL_GITHUB_URL
            + "'><i class='fa fa-github-square' style='font-size:24px'></i></a>";

    public static final String ICON_CAMERA = "<i class=\"fa fa-camera\" aria-hidden=\"true\"></i>";

    public static final String ICON_BROWSER_PREFIX = "<i class=\"fa fa-";
    public static final String ICON_BROWSER_SUFFIX = "\" aria-hidden=\"true\"></i>";
    /* ICONS - END */

//    public static String getExtentReportFilePath() {
//        if (EXTENT_REPORT_FILE_PATH.isEmpty()) {
//            EXTENT_REPORT_FILE_PATH = ReportUtils.createExtentReportPath();
//        }
//        return EXTENT_REPORT_FILE_PATH;
//    }
}
