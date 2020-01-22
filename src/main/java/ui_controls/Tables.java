package ui_controls;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Tables extends BaseControl {

    private By tableRootLocator;

    public Tables(WebDriver driver, By tableRootLocator) {
        super(driver);
        this.tableRootLocator = tableRootLocator;
    }

    public WebElement getDataTable(int rowIndx, int colIndx) {
        try {
            List<WebElement> rows = getTableRootElement().findElements(By.tagName("tr"));
            WebElement row = rows.get(rowIndx -1);
            List<WebElement> columns = row.findElements(By.tagName("td"));
            WebElement cell = columns.get(colIndx -1);
            return cell;
            //return columns.get(colIndx - 1);
        } catch (IndexOutOfBoundsException exception) {
            throw new IllegalArgumentException(
                    String.format("Row %d or col %d doesn't exist", rowIndx, colIndx));
        } catch (TimeoutException timeout) {
            throw new IllegalStateException("Table wasn't loaded");
        }
    }

    private WebElement getTableRootElement() {
        return $(tableRootLocator);
    }
}
