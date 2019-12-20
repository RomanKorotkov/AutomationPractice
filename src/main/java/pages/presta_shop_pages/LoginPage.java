package pages.presta_shop_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static pages.presta_shop_pages.BoolConditions.IN_URL;
import static pages.presta_shop_pages.Conditions.*;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By email = By.id("email");

    private By password = By.id("passwd");

    private By submit = By.id("SubmitLogin");

    public void fillLogInForm(String mail, String pass) {
        waitFor("http://automationpractice.com/index.php?controller=authentication&back=my-account", IN_URL);
        $(email, VISIBLE).sendKeys(mail);
        $(password, PRESENCE).sendKeys(pass);
        $(submit, CLICKABLE).click();
    }
}
