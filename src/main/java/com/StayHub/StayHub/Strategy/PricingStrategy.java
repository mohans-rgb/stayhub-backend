package com.StayHub.StayHub.Strategy;

import com.StayHub.StayHub.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}

