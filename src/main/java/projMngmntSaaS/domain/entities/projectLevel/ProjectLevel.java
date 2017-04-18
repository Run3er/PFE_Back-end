package projMngmntSaaS.domain.entities.projectLevel;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * This provides common project level properties.
 */
@MappedSuperclass
public abstract class ProjectLevel
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected UUID id;

    @Column(nullable = false)
    protected String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
    protected ProjectLevelUpdate pendingUpdate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProjectLevelUpdate> archivedUpdates = new HashSet<>();

    protected ProjectLevel() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectLevelUpdate getPendingUpdate() {
        return pendingUpdate;
    }

    public void setPendingUpdate(ProjectLevelUpdate pendingUpdate) {
        this.pendingUpdate = pendingUpdate;
    }

    public Set<ProjectLevelUpdate> getArchivedUpdates() {
        return archivedUpdates;
    }
}
