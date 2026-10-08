package com.lovable_clone.service.impl;

import com.lovable_clone.dto.projectmember.InviteMemberRequest;
import com.lovable_clone.dto.projectmember.ProjectMemberResponse;
import com.lovable_clone.dto.projectmember.UpdateMemberRoleRequest;
import com.lovable_clone.entity.ProjectMember;
import com.lovable_clone.mapper.ProjectMemberMapper;
import com.lovable_clone.repository.ProjectMemberRepository;
import com.lovable_clone.repository.ProjectRepository;
import com.lovable_clone.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;

    @Override
    public List<ProjectMemberResponse> getProjectMembers(Long projectId, Long userId) {
        List<ProjectMember> projectMembers = projectMemberRepository.findByIdProjectId(projectId);
        return projectMemberMapper.toProjectMemberResponseFromMember(projectMembers);
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
