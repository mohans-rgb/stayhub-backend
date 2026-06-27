package com.StayHub.StayHub.Service;

import com.StayHub.StayHub.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);

}