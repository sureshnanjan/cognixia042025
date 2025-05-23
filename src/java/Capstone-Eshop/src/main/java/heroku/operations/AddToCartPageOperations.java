package heroku.operations;
public interface AddToCartPageOperations {
    /**
     * Selects a brand filter dropdown.
     */
    void clickBrandDropdown();
    /**
     * Chooses a specific brand from the brand dropdown.
     */
    void selectBrand();
    /**
     * Selects a type filter dropdown.
     */
    void clickTypeDropdown();
    /**
     * Chooses a specific type from the type dropdown.
     */
    void selectType();
    /**
     * Clicks the arrow/search/submit button to apply the selected filters.
     */
    void clickFilterApplyButton();
    /**
     * Clicks the Add to Basket button for a listed item.
     */
    void clickAddToBasket();
    /**
     * Performs the full flow:
     * select brand → select type → apply filter → add to cart.
     */
    void addItemToCart();
}

