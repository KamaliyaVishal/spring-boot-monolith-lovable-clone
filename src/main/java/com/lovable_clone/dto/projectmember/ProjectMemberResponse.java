package com.lovable_clone.dto.projectmember;

import com.lovable_clone.enums.ProjectRole;

import java.time.Instant;

public record ProjectMemberResponse(
        Long userId,
        String username,
        String name,
        ProjectRole role,
        Instant invitedAt
) {
}
