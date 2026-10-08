package com.lovable_clone.service.impl;

import com.lovable_clone.dto.projectmember.InviteMemberRequest;
import com.lovable_clone.dto.projectmember.ProjectMemberResponse;
import com.lovable_clone.dto.projectmember.UpdateMemberRoleRequest;
import com.lovable_clone.entity.Project;
import com.lovable_clone.entity.ProjectMember;
import com.lovable_clone.entity.ProjectMemberId;
import com.lovable_clone.entity.User;
import com.lovable_clone.mapper.ProjectMemberMapper;
import com.lovable_clone.repository.ProjectMemberRepository;
import com.lovable_clone.repository.ProjectRepository;
import com.lovable_clone.repository.UserRepository;
import com.lovable_clone.service.ProjectMemberService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectMemberServiceImpl implements ProjectMemberService {

    ProjectMemberRepository projectMemberRepository;
    ProjectRepository projectRepository;
    ProjectMemberMapper projectMemberMapper;
    UserRepository userRepository;

    @Override
    public List<ProjectMemberResponse> getProjectMembers(Long projectId, Long userId) {
        List<ProjectMember> projectMembers = projectMemberRepository.findByIdProjectId(projectId);
        return projectMemberMapper.toListOfProjectMemberResponse(projectMembers);
    }

    @Override
    public ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);

        User invitee = userRepository.findByUsername(request.username()).orElseThrow();
        if (invitee.getId().equals(userId))
            throw new RuntimeException("Cannot invite yourself");

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, invitee.getId());
        if (projectMemberRepository.existsById(projectMemberId))
            throw new RuntimeException("Cannot invite once again");

        ProjectMember member = ProjectMember.builder()
                .id(projectMemberId)
                .project(project)
                .user(invitee)
                .projectRole(request.role())
                .invitedAt(Instant.now())
                .build();
        projectMemberRepository.save(member);

        return projectMemberMapper.toProjectMemberResponse(member);
    }

    @Override
    public ProjectMemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        return null;
    }

    @Override
    public ProjectMemberResponse deleteProjectMember(Long projectId, Long memberId, Long userId) {
        return null;
    }

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();
    }
}
