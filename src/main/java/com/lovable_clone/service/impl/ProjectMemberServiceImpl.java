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
@Transactional(readOnly = true)
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
    @Transactional(rollbackFor = Exception.class)
    public ProjectMemberResponse inviteMember(Long projectId, InviteMemberRequest request, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        if (!project.getOwner().getId().equals(userId))
            throw new RuntimeException("Not allowed");

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
    @Transactional(rollbackFor = Exception.class)
    public ProjectMemberResponse updateMemberRole(Long projectId, Long memberId, UpdateMemberRoleRequest request, Long userId) {
        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        ProjectMember projectMember = projectMemberRepository.findById(projectMemberId).orElseThrow();
        projectMember.setProjectRole(request.role());
        projectMemberRepository.save(projectMember);
        return projectMemberMapper.toProjectMemberResponse(projectMember);
    }

    @Override
    public void deleteProjectMember(Long projectId, Long memberId, Long userId) {
        Project project = getAccessibleProjectById(projectId, userId);
        if (!project.getOwner().getId().equals(userId))
            throw new RuntimeException("Not allowed");

        ProjectMemberId projectMemberId = new ProjectMemberId(projectId, memberId);
        if (!projectMemberRepository.existsById(projectMemberId))
            throw new RuntimeException("Can't find provided member");

        projectMemberRepository.deleteById(projectMemberId);
    }

    public Project getAccessibleProjectById(Long projectId, Long userId) {
        return projectRepository.findAccessibleProjectById(projectId, userId).orElseThrow();
    }
}
