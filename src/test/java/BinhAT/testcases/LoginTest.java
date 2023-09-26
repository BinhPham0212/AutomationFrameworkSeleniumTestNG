package BinhAT.testcases;

import BinhAT.common.BaseTest;
import BinhAT.dataproviders.DataLogin;
import BinhAT.drivers.DriverManager;
import BinhAT.helpers.CaptureHelper;
import BinhAT.helpers.ExcelHelper;
import BinhAT.listeners.TestListener;
import BinhAT.pages.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Hashtable;
@Listeners(TestListener.class)         //Goi class Listener mới thiết lập
public class LoginTest extends BaseTest {

    //Khởi tạo đối tượng dùng chung cho toàn bộ class
    LoginPage loginPage;

    @Test(dataProvider = "data_provider_login_successful", dataProviderClass = DataLogin.class)    //Truyền name dataprovider và class dataprovider
    public void loginTestSuccesful(Hashtable< String, String > data) {
        //Khởi tạo đối tượng trang LoginPage
        //truyền driver từ BaseTest
        loginPage = new LoginPage();
        //gọi hàm "Login"
        loginPage.login(data.get("EMAIL"), data.get("PASSWORD"));
    }


    @Test(dataProvider = "data_provider_login_invalidemail", dataProviderClass = DataLogin.class)    //Truyền name dataprovider và class dataprovider
    public void loginTestWithInvalidEmail(Hashtable< String, String > data) {
        //Khởi tạo đối tượng trang LoginPage
        //truyền driver từ BaseTest
        loginPage = new LoginPage();
        //gọi hàm "Login"
        loginPage.login(data.get("EMAIL"), data.get("PASSWORD"));
    }

    @Test(dataProvider = "data_provider_login_invalidpassword", dataProviderClass = DataLogin.class)    //Truyền name dataprovider và class dataprovider
    public void loginTestWithInvalidPassword(Hashtable< String, String > data) {
        //Khởi tạo đối tượng trang LoginPage
        //truyền driver từ BaseTest
        loginPage = new LoginPage();
        //gọi hàm "Login"
        loginPage.login(data.get("EMAIL"), data.get("PASSWORD"));
    }
}


