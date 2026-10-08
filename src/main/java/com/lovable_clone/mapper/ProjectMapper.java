package com.lovable_clone.mapper;

import com.lovable_clone.dto.project.ProjectResponse;
import com.lovable_clone.dto.project.ProjectSummaryResponse;
import com.lovable_clone.entity.Project;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectMapper {

    ProjectResponse toProjectResponse(Project project);

    ProjectSummaryResponse toProjectSummaryResponse(Project project);

    List<ProjectSummaryResponse> toListOfProjectSummaryResponse(List<Project> projects);

}
