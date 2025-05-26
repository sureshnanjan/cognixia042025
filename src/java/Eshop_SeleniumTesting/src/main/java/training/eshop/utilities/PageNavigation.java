/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  This class represents the navigation controls on a paginated page in the eShop application.
 *  It keeps track of the visibility of the Previous and Next buttons, and the display status of
 *  the pagination info text (e.g., "Showing 1-10 of 100").
 * =======================================================================================
 */

package training.eshop.utilities;

public class PageNavigation {
    public boolean previousBtnVisible;
    public boolean nextBtnVisible;
    public boolean showingTextVisible;

    /**
     * Simulates clicking the Previous button to navigate to the previous page.
     */
    public void Previous() {
        System.out.println("Navigated to previous page");
    }

    /**
     * Simulates clicking the Next button to navigate to the next page.
     */
    public void Next() {
        System.out.println("Navigated to next page");
    }

    /**
     * Sets the visibility of the "showing" text based on the provided text content.
     * @param text The pagination info text (e.g., "Showing 1-10 of 100")
     */
    public void setShowingText(String text) {
        this.showingTextVisible = text != null && !text.trim().isEmpty();
    }
}
