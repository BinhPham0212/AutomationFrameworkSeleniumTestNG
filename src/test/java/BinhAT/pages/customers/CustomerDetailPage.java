package BinhAT.pages.customers;

import static BinhAT.keywords.WebUI.*;

import org.testng.Assert;

public class CustomerDetailPage extends AddCustomerPage{
    //**Data trong nội bộ xác thực trang Customer */
    private String PAGE_URL = "https://crm.anhtester.com/admin/clients";
    private String PAGE_TEXT = "Customers Summary";   //Chỉ trong trang này mới có
    //**Các Objects thuộc Customer details page extends từ AddCustomerPage */

    //**Hàm xử lý cho các chức năng thuộc Customer Page */
    public void checkCustomerDetail(String expectedCustomerName) {
        //Kiểm tra phải dùng Equals chứ không nên dùng Contains
        System.out.println(getAttributeELement(inputCompanyName,"value"));
        System.out.println(getAttributeELement(inputAddress,"value"));
        System.out.println(getAttributeELement(inputPhoneNumber,"value"));

        Assert.assertEquals(getAttributeELement(inputCompanyName,"value"),expectedCustomerName,"Company name not match.");
        Assert.assertEquals(getAttributeELement(inputAddress,"value"),"Viet Nam","Address not match.");
        Assert.assertEquals(getAttributeELement(inputPhoneNumber,"value"),"0368922198","State not match.");
    }

    public void editCustomerDetail(String editCountryField) {
        clickElement(dropdownCountry);
        setTextEnter(inputCountry, editCountryField);
        clickElement(dropdownCountry);
        clickElement(buttonSave);
    }

    public void checkAfterEditCustomerDetail(String editCountryField, String countryFieldExpected) {
        Assert.assertEquals(editCountryField,countryFieldExpected,"Country after edit not match.");
    }
}
