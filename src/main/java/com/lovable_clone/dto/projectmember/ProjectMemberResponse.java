package com.lovable_clone.dto.projectmember;

import com.lovable_clone.enums.ProjectRole;

import java.time.Instant;

public record ProjectMemberResponse(
        Long userId,
        String email,
        String name,
        String avatarUrl,
        ProjectRole role,
        Instant invitedAt
) {
}
