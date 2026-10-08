package com.lovable_clone.controller;

import com.lovable_clone.dto.billing.UsageTodayResponse;
import com.lovable_clone.dto.usage.PlanLimitsResponse;
import com.lovable_clone.service.UsageService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usage")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UsageController {

    UsageService usageService;

    @GetMapping("/today")
    public ResponseEntity<UsageTodayResponse> getTodayUsage() {
        Long userId = 1L;
        return ResponseEntity.ok(usageService.getTodayUsageOfUser(userId));
    }

    @GetMapping("/limits")
    public ResponseEntity<PlanLimitsResponse> getPlanLimits() {
        Long userId = 1L;
        return ResponseEntity.ok(usageService.getCurrentSubscriptionLimitsOfUser(userId));
    }
}
