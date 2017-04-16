package projMngmntSaaS.domain.entities;


import projMngmntSaaS.domain.entities.enums.ProjectRole;
import projMngmntSaaS.domain.entities.projectLevel.Project;

import javax.persistence.*;
import java.util.UUID;

/**
 * Outside-contributing-user specific project-contribution details entity.
 * This class distinguishes an outside contributing user from the rest of the users (logically defaulting to being
 * tenant employees).
 */
@Entity
// Avoid redundant contributions
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "project_id", "role"})})
public class Contribution
{
    @Id
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User contributor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(nullable = false, name = "role")
    private ProjectRole role;

    @ManyToOne(optional = false)
    private ContributorOrganization organization;

    public Contribution() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public User getContributor() {
        return contributor;
    }

    public void setContributor(User contributor) {
        this.contributor = contributor;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectRole getRole() {
        return role;
    }

    public void setRole(ProjectRole role) {
        this.role = role;
    }

    public ContributorOrganization getOrganization() {
        return organization;
    }

    public void setOrganization(ContributorOrganization organization) {
        this.organization = organization;
    }
}