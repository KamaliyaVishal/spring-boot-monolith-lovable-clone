package com.lovable_clone.service.impl;

import com.lovable_clone.dto.billing.PlanResponse;
import com.lovable_clone.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
