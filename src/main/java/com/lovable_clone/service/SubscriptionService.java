package com.lovable_clone.service;

import com.lovable_clone.dto.billing.CheckoutRequest;
import com.lovable_clone.dto.billing.CheckoutResponse;
import com.lovable_clone.dto.billing.PortalResponse;
import com.lovable_clone.dto.billing.SubscriptionResponse;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
