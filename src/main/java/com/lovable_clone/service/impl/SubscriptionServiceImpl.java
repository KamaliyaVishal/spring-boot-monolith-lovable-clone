package com.lovable_clone.service.impl;

import com.lovable_clone.dto.billing.CheckoutRequest;
import com.lovable_clone.dto.billing.CheckoutResponse;
import com.lovable_clone.dto.billing.PortalResponse;
import com.lovable_clone.dto.billing.SubscriptionResponse;
import com.lovable_clone.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
