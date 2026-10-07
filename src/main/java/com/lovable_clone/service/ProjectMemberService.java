package com.lovable_clone.service;

import com.lovable_clone.dto.projectmember.InviteMemberRequest;
import com.lovable_clone.dto.projectmember.MemberResponse;
import com.lovable_clone.entity.ProjectMember;

import java.util.List;

public interface ProjectMemberService {

    List<ProjectMember> getProjectMembers(Long projectId, Long userId);

    MemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId);

    MemberResponse updateMemberRole(Long projectId, Long memberId, InviteMemberRequest request, Long userId);

    MemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId);
}
