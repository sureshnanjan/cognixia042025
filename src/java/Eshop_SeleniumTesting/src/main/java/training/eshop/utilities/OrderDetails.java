/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  Data model class representing the details of an order in the eShop application.
 * =======================================================================================
 */

package training.eshop.utilities;

public class OrderDetails {
    private String orderId;
    private String orderDate;
    private String orderStatus;
    private String orderTotal;

    /**
     * Constructor to initialize order details.
     * @param orderId Unique identifier for the order
     * @param orderDate Date when the order was placed
     * @param orderStatus Current status of the order (e.g., Shipped, Pending)
     * @param orderTotal Total cost of the order as a String (e.g., "$49.99")
     */
    public OrderDetails(String orderId, String orderDate, String orderStatus, String orderTotal) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
    }

    /** @return The unique order ID */
    public String getOrderId() { return orderId; }

    /** @return The date of the order */
    public String getOrderDate() { return orderDate; }

    /** @return The status of the order */
    public String getOrderStatus() { return orderStatus; }

    /** @return The total amount of the order */
    public String getOrderTotal() { return orderTotal; }
}
