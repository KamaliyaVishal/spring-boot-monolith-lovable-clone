package com.lovable_clone.service;

import com.lovable_clone.dto.billing.UsageTodayResponse;
import com.lovable_clone.dto.usage.PlanLimitsResponse;

public interface UsageService {

    UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
