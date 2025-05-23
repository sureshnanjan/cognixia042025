package training.eshop.seleniumpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import training.eshop.operations.LandingPageOperations;
import training.eshop.utilities.*;
import utilities.WebElementInteractions;

import java.util.List;

public class LandingPage implements LandingPageOperations {
    WebDriver browser;
    private By logoidentifier;
    private By login_link_identifiers;

    public LandingPage() {
        this.browser = new ChromeDriver();
        logoidentifier = By.xpath("/html/body/div/header/div/article/section[1]/a/img");
        WebElementInteractions.navigatetoUrlUtil("http://35.193.6.1:5106", this.browser);
        //this.browser.get("http://35.193.6.1:5106");

    }

    @Override
    public EshopLogo getLogoDetails() {
        WebElement logo = WebElementInteractions.findElementUtil(logoidentifier, browser);
        return new EshopLogo(
        logo.getDomAttribute("src"),
        logo.getDomAttribute("alt")
        );
    }

    @Override
    public void enterLoginPage() {

    }

    @Override
    public void accessNextPage() {

    }

    @Override
    public void accessPreviousPage() {

    }

    @Override
    public EshopCart getShoppingCartDetails() {
        return null;
    }

    @Override
    public BrandFilter getBrandFilter() {
        return null;
    }

    @Override
    public List<String> getTypeFilters() {
        return null;
    }

    @Override
    public List<CatalogueItem> getCatalogueItems() {
        return null;
    }

    @Override
    public PageNavigation getPaginationDeatils() {
        return null;
    }
}
