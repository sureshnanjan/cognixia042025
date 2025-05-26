/*
 * Copyright (c) 2025 Athesh Alagarsamy, Pavithra Rajendiran, Subalakshmi T, Vaishnavi Sivakumar
 * All rights reserved.
 *
 * This is a base interface for eShop operations.
 * It can be used as a fallback or reference point when other operations fail or as a common parent for shared structure.
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package training.eshop.operations;

/**
 * Marker interface for core eShop operations.
 * Can be referenced when complex implementations fail or as a base structure for shared contracts.
 */
public interface EshopOperations {
    String getTitle();
}
