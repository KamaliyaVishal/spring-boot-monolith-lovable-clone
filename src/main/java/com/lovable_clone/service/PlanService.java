package com.lovable_clone.service;

import com.lovable_clone.dto.billing.PlanResponse;

import java.util.List;

public interface PlanService {
     List<PlanResponse> getAllActivePlans();
}
