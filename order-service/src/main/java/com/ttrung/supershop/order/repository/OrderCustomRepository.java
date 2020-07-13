package com.ttrung.supershop.order.repository;

/**
 * Copyright (c) 2020 Absolute Software Corporation. All rights reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means,
 * electronic, mechanical or otherwise, is prohibited without the prior written
 * consent of the copyright owner.
 */
public interface OrderCustomRepository {

    /**
     * Cancel the specified order of the given user
     * @param userId
     * @param orderId
     * @return true if the order of the specified user exists, and is modified, false otherwise.
     */
    boolean cancelOrder(String userId, String orderId);
}
