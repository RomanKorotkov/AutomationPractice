package pages.PrestaShopPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static pages.PrestaShopPages.Conditions.*;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By email = By.id("email");

    private By password = By.id("passwd");

    private By submit = By.id("SubmitLogin");

    public void fillLogInForm(String mail, String pass) {
        $(email, VISIBLE).sendKeys(mail);
        $(password, PRESENCE).sendKeys(pass);
        $(submit, CLICKABLE).click();
    }
}
