package pages.presta_shop_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static pages.presta_shop_pages.BoolConditions.IN_TITLE;
import static pages.presta_shop_pages.Conditions.*;

public class SignInForm extends BasePage {

    public SignInForm(WebDriver driver) {
        super(driver);
    }

    Random random = new Random();

    private By mail = By.id("email_create");

    private By submit_btn = By.id("SubmitCreate");
    private By account_error = By.id("create_account_error");

    private By gender = By.id("id_gender1");

    private By cust_name = By.id("customer_firstname");

    private By cust_last_name = By.id("customer_lastname");

    private By password = By.id("passwd");

    private By firstname = By.id("firstname");

    private By lastname = By.id("lastname");

    private By address = By.id("address1");

    private By city = By.id("city");

    private By state = By.xpath("//*[@id=\"id_state\"]/option[3]");

    private By postcode = By.id("postcode");

    private By other = By.id("other");

    private By phone = By.id("phone");

    private By phone_mobile = By.id("phone_mobile");

    private By alias = By.id("alias");

    private By submit_account = By.id("submitAccount");
    private By account_header = By.xpath("//h1[contains(text(),'My account')]");

    public void fillRegisterForm() {
        waitFor("Login - My Store", IN_TITLE);
        $(mail, VISIBLE).sendKeys(random.nextInt(10000000) + "@mail.com");
        $(submit_btn, CLICKABLE).click();
        $(gender, CLICKABLE).click();
        $(cust_name, PRESENCE).sendKeys("QA");
        $(cust_last_name, PRESENCE).sendKeys("QA");
        $(password, PRESENCE).sendKeys(random.nextInt() + "qa");
        $(firstname, PRESENCE).clear();
        $(firstname, PRESENCE).sendKeys("Tester");
        $(lastname, PRESENCE).clear();
        $(lastname, PRESENCE).sendKeys("Tester");
        $(address, PRESENCE).sendKeys(random.nextInt() + "IDEA");
        $(city, PRESENCE).sendKeys(random.nextInt() + "Java");
        $(state, CLICKABLE).click();
        $(postcode, PRESENCE).sendKeys("23895");
        $(other, PRESENCE).sendKeys("Testing");
        $(phone, PRESENCE).sendKeys("056245879");
        $(phone_mobile, PRESENCE).sendKeys("0236589741");
        $(alias, PRESENCE).clear();
        $(alias, PRESENCE).sendKeys("Testing");
        $(submit_account, CLICKABLE).click();
    }

    public String getHeaderTextAfterRegistration() {
        return $(account_header, VISIBLE).getText();
    }

    public String getErrorTextIfUserExist() {
        return $(account_error, VISIBLE).getText();
    }

    public void signWithExistedEmail() {
        $(mail, VISIBLE).sendKeys("8767915@mail.com");
        $(submit_btn, CLICKABLE).click();
    }
}
