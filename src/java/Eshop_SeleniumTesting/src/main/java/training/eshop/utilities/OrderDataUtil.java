/*
 * =======================================================================================
 *  Copyright (c) 2025
 *  Team Members: Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 *
 *  Utility class to provide mock OrderDetails data for testing or development purposes.
 * =======================================================================================
 */

package training.eshop.utilities;

public class OrderDataUtil {

    /**
     * Provides a mock OrderDetails instance with sample data.
     * @return OrderDetails object containing mock order information
     */
    public static OrderDetails getMockOrderDetails() {
        return new OrderDetails("ORD123", "2025-05-23", "Shipped", "$49.99");
    }
}
