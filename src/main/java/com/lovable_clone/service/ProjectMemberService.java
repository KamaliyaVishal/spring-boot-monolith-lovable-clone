package com.lovable_clone.service;

import com.lovable_clone.dto.projectmember.InviteMemberRequest;
import com.lovable_clone.dto.projectmember.ProjectMemberResponse;
import com.lovable_clone.dto.projectmember.UpdateMemberRoleRequest;
import com.lovable_clone.entity.ProjectMember;

import java.util.List;

public interface ProjectMemberService {

    List<ProjectMemberResponse> getProjectMembers(Long projectId, Long userId);

    ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    ProjectMemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId);

    ProjectMemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId);
}
