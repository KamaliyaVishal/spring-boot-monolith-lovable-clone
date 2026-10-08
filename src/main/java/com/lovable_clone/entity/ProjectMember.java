package com.lovable_clone.entity;

import com.lovable_clone.enums.ProjectRole;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_members")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectMember {

    /**
     * We explicitly map this join table as a distinct entity using @EmbeddedId and @MapsId
     * INSTEAD of a traditional @ManyToMany relationship.
     * This allows us to extend this join table with extra columns (like projectRole, invitedAt etc.)
     * and prevents Hibernate from running inefficient collection-clearing queries.
     **/
    @EmbeddedId
    ProjectMemberId id;

    @ManyToOne
    @MapsId("projectId")
    Project project;

    @ManyToOne
    @MapsId("userId")
    User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProjectRole projectRole;

    Instant invitedAt;

    Instant acceptedAt;
}
