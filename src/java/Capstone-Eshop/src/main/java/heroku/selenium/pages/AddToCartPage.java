package heroku.selenium.pages;
import heroku.operations.AddToCartPageOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import static heroku.utilities.WebElementInteractions.*;

public class AddToCartPage implements AddToCartPageOperations {

    private WebDriver driver;

    // Locators
    private final By brandDropdownLocator = By.xpath("//*[@id=\"CatalogModel_BrandFilterApplied\"]");
    private final By typeDropdownLocator = By.xpath("//*[@id=\"CatalogModel_TypesFilterApplied\"]");
    private final By filterButtonLocator = By.xpath("/html/body/div/section[2]/div/form/input");
    private final By addToBasketButtonLocator = By.xpath("/html/body/div/div/div[2]/div[1]/form/input[1]");

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("http://35.193.6.1:5106/");
    }

    @Override
    public void clickBrandDropdown() {
        // No explicit action needed to open dropdown; use selectBrand to choose an option
    }

    @Override
    public void selectBrand() {
        Select brandSelect = new Select(findElementUtil(brandDropdownLocator, driver));
        brandSelect.selectByIndex(1); // Selecting the second option (index starts at 0)
    }

    @Override
    public void clickTypeDropdown() {
        // No explicit action needed to open dropdown; use selectType to choose an option
    }

    @Override
    public void selectType() {
        Select typeSelect = new Select(findElementUtil(typeDropdownLocator, driver));
        typeSelect.selectByIndex(3); // Selecting the fourth option
    }
    public void selectBrandNoResults() {
        Select brandSelect = new Select(findElementUtil(brandDropdownLocator, driver));
        int brandCount = brandSelect.getOptions().size();

        if (brandCount > 1) {
            brandSelect.selectByIndex(brandCount - 1); // last option assumed to return no results
        }
    }

    public void selectTypeNoResults() {
        Select typeSelect = new Select(findElementUtil(typeDropdownLocator, driver));
        int typeCount = typeSelect.getOptions().size();

        if (typeCount > 1) {
            typeSelect.selectByIndex(typeCount - 1); // last option assumed to return no results
        }
    }



    @Override
    public void clickFilterApplyButton() {
        clickElementUtil(findElementUtil(filterButtonLocator, driver));
    }

    @Override
    public void clickAddToBasket() {
        clickElementUtil(findElementUtil(addToBasketButtonLocator, driver));
    }

    @Override
    public void addItemToCart() {
        selectBrand();
        selectType();
        clickFilterApplyButton();
        clickAddToBasket();
    }
}
