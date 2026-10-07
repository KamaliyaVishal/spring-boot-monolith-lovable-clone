package com.lovable_clone.dto.projectmember;

import com.lovable_clone.enums.ProjectRole;

public record InviteMemberRequest(
        String email,
        ProjectRole role
) {
}
