package com.lovable_clone.service.impl;

import com.lovable_clone.dto.projectmember.InviteMemberRequest;
import com.lovable_clone.dto.projectmember.ProjectMemberResponse;
import com.lovable_clone.dto.projectmember.UpdateMemberRoleRequest;
import com.lovable_clone.service.ProjectMemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {
    @Override
    public List<ProjectMemberResponse> getProjectMembers(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        return null;
    }

    @Override
    public ProjectMemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        return null;
    }

    @Override
    public ProjectMemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId) {
        return null;
    }
}
