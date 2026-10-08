package com.lovable_clone.service.impl;

import com.lovable_clone.dto.project.ProjectRequest;
import com.lovable_clone.dto.project.ProjectResponse;
import com.lovable_clone.dto.project.ProjectSummaryResponse;
import com.lovable_clone.entity.Project;
import com.lovable_clone.entity.User;
import com.lovable_clone.mapper.ProjectMapper;
import com.lovable_clone.repository.ProjectRepository;
import com.lovable_clone.repository.UserRepository;
import com.lovable_clone.service.ProjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ProjectServiceImpl implements ProjectService {

    ProjectRepository projectRepository;
    UserRepository userRepository;
    ProjectMapper projectMapper;

    @Override
    public List<ProjectSummaryResponse> getUserProjects(Long userId) {
        // return projectRepository.findAllAccessibleByUser(userId)
        //         .stream()
        //         .map(projectMapper::toProjectSummaryResponse)
        //         .collect(Collectors.toList());

        var projects = projectRepository.findAllAccessibleByUser(userId);
        return projectMapper.toListOfProjectSummaryResponse(projects);
    }

    @Override
    public ProjectResponse getUserProjectById(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectResponse createProject(ProjectRequest request, Long userId) {
        User owner = userRepository.findById(userId).orElseThrow();

        Project project = Project.builder()
                .name(request.name())
                .owner(owner)
                .build();

        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProjectResponse updateProject(Long id, ProjectRequest request, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if (!project.getOwner().getId().equals(userId))
            throw new RuntimeException("You are not allowed to update the project");

        project.setName(request.name());
        project = projectRepository.save(project);
        return projectMapper.toProjectResponse(project);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void softDelete(Long id, Long userId) {
        Project project = getAccessibleProjectById(id, userId);
        if (!project.getOwner().getId().equals(userId))
            throw new RuntimeException("You are not allowed to delete the project");

        project.setDeletedAt(Instant.now());
        projectRepository.save(project);
    }

    private Project getAccessibleProjectById(Long id, Long userId) {
        return projectRepository.findAccessibleProjectById(id, userId).orElseThrow();
    }
}
