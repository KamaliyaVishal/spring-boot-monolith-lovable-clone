package com.lovable_clone.service.impl;

import com.lovable_clone.dto.billing.UsageTodayResponse;
import com.lovable_clone.dto.usage.PlanLimitsResponse;
import com.lovable_clone.service.UsageService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {

    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
