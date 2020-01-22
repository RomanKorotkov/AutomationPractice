package ui_controls;

import api.BaseAPI;
import org.openqa.selenium.WebDriver;

public class BaseControl implements BaseAPI {

    protected WebDriver driver;

    public BaseControl(WebDriver driver){
        this.driver = driver;
    }

    @Override
    public WebDriver getDriver(){
        return driver;
    }
}
