/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class represents the Landing Page of the eShop application.
 *  It provides methods to interact with the landing page elements such as
 *  logo, banner, login, basket, brand filters, pagination, catalogue items, and footer.
 *  The class uses Selenium WebDriver to automate browser actions and relies on
 *  WebElementInteractions utility for element interactions and waits.
 * =======================================================================================
 */

package training.eshop.seleniumpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import training.eshop.operations.EshopOperations;
import training.eshop.operations.LandingPageOperations;
import training.eshop.utilities.*;

import java.util.ArrayList;
import java.util.List;

import static training.eshop.utilities.WebElementInteractions.*;

/**
 * LandingPage class implements LandingPageOperations interface.
 * It defines methods to automate user interactions with the landing page components.
 */
public class LandingPage implements LandingPageOperations {
    WebDriver browser;

    // Header and navigation locators
    private By logoidentifier;
    private By banneridentifier;
    private By login_link_identifiers;
    private By basket_link_identifiers;
    private By brand_filter_identifiers;
    private By brand_type_identifiers;
    private By basketText;

    // Pagination locators
    private By prevBtn;
    private By nextBtn;
    private By showingText;

    // Catalogue item locators
    private By productImage;
    private By addToBasketBtn;
    private By productName;
    private By productPrice;

    // Filter locators
    private By brandFilter;
    private By brandDotNet;
    private By typeFilter;
    private By typeSheet;
    private By sendBtn;
    private By filteredItemName;

    // Footer locator
    private By footerLocator;

    /**
     * Constructor initializes the browser, sets all required locators,
     * and navigates to the landing page of the eShop application.
     */
    public LandingPage() {
        this.browser = new ChromeDriver();
        browser.manage().window().maximize();

        // Element initializations
        logoidentifier = By.xpath("/html/body/div/header/div/article/section[1]/a/img");
        banneridentifier = By.cssSelector("section.esh-catalog-hero");
        login_link_identifiers = By.xpath("/html/body/div/header/div/article/section[2]/div/section/div/a");
        basket_link_identifiers = By.xpath("/html/body/div/header/div/article/section[3]/a");
        brand_filter_identifiers = By.xpath("//*[@id=\"CatalogModel_BrandFilterApplied\"]/option");
        brand_type_identifiers = By.xpath("//*[@id=\"CatalogModel_TypesFilterApplied\"]/option");
        footerLocator = By.xpath("/html/body/div/footer/div/article/section[2]/div");
        basketText = By.xpath("/html/body/div/div/h3");

        // Pagination
        prevBtn = By.xpath("//*[@id=\"Previous\"]");
        nextBtn = By.xpath("//*[@id=\"Next\"]");
        showingText = By.xpath("/html/body/div/div/div[1]/div/article/nav/div[2]/span");

        // Product information
        productImage = By.xpath("/html/body/div/div/div[2]/div[1]/form/img");
        addToBasketBtn = By.xpath("/html/body/div/div/div[2]/div[1]/form/input[1]");
        productName = By.xpath("/html/body/div/div/div[2]/div[1]/form/div[1]/span");
        productPrice = By.xpath("/html/body/div/div/div[2]/div[1]/form/div[2]/span");

        // Filters
        brandFilter = By.xpath("//*[@id=\"CatalogModel_BrandFilterApplied\"]");
        brandDotNet = By.xpath("//*[@id=\"CatalogModel_BrandFilterApplied\"]/option[2]");
        typeFilter = By.xpath("//*[@id=\"CatalogModel_TypesFilterApplied\"]");
        typeSheet = By.xpath("//*[@id=\"CatalogModel_TypesFilterApplied\"]/option[3]");
        sendBtn = By.xpath("/html/body/div/section[2]/div/form/input");
        filteredItemName = By.xpath("/html/body/div/div/div[2]/div[1]/form/div[1]/span");

        // Open the landing page
        WebElementInteractions.navigatetoUrlUtil("http://35.193.6.1:5106", this.browser);
    }

    /**
     * Fetches the current page title.
     * @return Title string or empty string on failure.
     */
    public String getTitle() {
        return getCurrentPageTitle(browser);
    }

    /**
     * Fetches logo element details like src and alt attributes.
     * @return EshopLogo object with image source and alt text.
     */
    @Override
    public EshopLogo getLogoDetails() {
        WebElement logo = findElementUtil(logoidentifier, browser);
        return new EshopLogo(
                logo.getDomAttribute("src"),
                logo.getDomAttribute("alt")
        );
    }

    /**
     * Clicks on login link and returns either LoginPage or ErrorPage object.
     * @return EshopOperations (LoginPage or ErrorPage).
     */
    @Override
    public EshopOperations enterLoginPage() {
        ClickLogin clickLogin = new ClickLogin(browser);
        clickLogin.doLogin();
        return getCurrentUrlUtil(browser).endsWith("Login") ? new LoginPage() : new ErrorPage(browser);
    }

    /**
     * Clicks on the basket link and verifies navigation.
     * @return BasketPage if successful, otherwise ErrorPage.
     */
    public String openBasketAndGetMessage() {
        WebElement basketLink = findElementUtil(basket_link_identifiers, browser);
        clickElementUtil(basketLink);

        WebElement message = findElementUtil(basketText, browser);
        return getTextUtil(message);
    }



    /**
     * Gets the background image style from the banner.
     * @return EshopBanner object containing CSS value.
     */
    public EshopBanner getBannerDetails() {
        WebElement banner = findElementUtil(banneridentifier, browser);
        return new EshopBanner(banner.getCssValue("background-image"));
    }

    /**
     * Retrieves brand filter options.
     * @return BrandFilter object with list of WebElement options.
     */
    public BrandFilter getBrandFilter() {
        List<WebElement> filters = findElementsUtil(brand_filter_identifiers, browser);
        return new BrandFilter(filters);
    }

    /**
     * Retrieves product type filter options.
     * @return BrandType object with type option elements.
     */
    @Override
    public BrandType getBrandType() {
        List<WebElement> types = findElementsUtil(brand_type_identifiers, browser);
        return new BrandType(types);
    }

    /**
     * Retrieves visibility state of pagination controls.
     * @return PageNavigation object with boolean values for pagination elements.
     */
    @Override
    public PageNavigation getPaginationDetails() {
        PageNavigation navigation = new PageNavigation();
        navigation.previousBtnVisible = isElementPresent(prevBtn, browser);
        navigation.nextBtnVisible = isElementPresent(nextBtn, browser);
        navigation.showingTextVisible = isElementPresent(showingText, browser);
        return navigation;
    }

    /**
     * Extracts and builds a list of CatalogueItem objects from visible products.
     * @return List of CatalogueItem representing the visible products.
     */
    @Override
    public List<CatalogueItem> getCatalogueItems() {
        List<CatalogueItem> items = new ArrayList<>();
        List<WebElement> names = findElementsUtil(productName, browser);

        for (WebElement nameElement : names) {
            CatalogueItem item = new CatalogueItem();
            item.nameVisible = nameElement.isDisplayed();
            item.item_name = getTextUtil(nameElement);
            item.imageVisible = isElementPresent(productImage, browser);
            item.addToCartBtnVisible = isElementPresent(addToBasketBtn, browser);
            item.priceVisible = isElementPresent(productPrice, browser);
            items.add(item);
        }
        return items;
    }

    /**
     * Applies filters for .NET brand and Sheet type, then extracts the filtered item.
     * @return Filtered CatalogueItem object.
     */
    @Override
    public CatalogueItem filterProducts() {
        CatalogueItem item = new CatalogueItem();

        clickElementUtil(findElementUtil(brandFilter, browser));
        sleep(1000);
        clickElementUtil(findElementUtil(brandDotNet, browser));

        clickElementUtil(findElementUtil(typeFilter, browser));
        sleep(1000);
        clickElementUtil(findElementUtil(typeSheet, browser));

        clickElementUtil(findElementUtil(sendBtn, browser));

        WebElementInteractions.sleep(2000);
        WebElement span = findElementUtil(filteredItemName, browser);
        item.item_name = getTextUtil(span);
        item.nameVisible = item.matchProductName("CUP<T> SHEET");

        return item;
    }

    /**
     * Scrolls to the footer section and retrieves footer text content.
     * @return Footer object with text verification result.
     */
    @Override
    public Footer getFooter() {
        Footer footer = new Footer();
        sleep(2000); // wait for the footer to load
        scrollBy(0, 2000, browser);
        WebElement element = findElementUtil(footerLocator, browser);
        footer.checkFooterText(element);
        return footer;
    }

    /**
     * Closes the browser session.
     */
    public void closeBrowser() {
        if (browser != null) {
            browser.quit();
        }
    }
}
