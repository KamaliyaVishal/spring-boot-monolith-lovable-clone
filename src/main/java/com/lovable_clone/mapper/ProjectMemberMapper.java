package com.lovable_clone.mapper;

import com.lovable_clone.dto.projectmember.ProjectMemberResponse;
import com.lovable_clone.entity.ProjectMember;
import com.lovable_clone.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectMemberMapper {

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "role", constant = "OWNER")
    ProjectMemberResponse toProjectMemberResponseFromOwner(User owner);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "username", source = "user.username")
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "role", source = "projectRole")
    List<ProjectMemberResponse> toProjectMemberResponseFromMember(List<ProjectMember> projectMembers);
}
