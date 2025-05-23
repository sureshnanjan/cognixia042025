package training.eshop.operations;

import training.eshop.utilities.*;

import java.util.List;

public interface LandingPageOperations {

    EshopLogo getLogoDetails();
    void enterLoginPage();

    void accessNextPage();

    void accessPreviousPage();

    EshopCart getShoppingCartDetails();

    BrandFilter getBrandFilter();

    List<String> getTypeFilters();

    List<CatalogueItem> getCatalogueItems();

    PageNavigation getPaginationDeatils();
}
