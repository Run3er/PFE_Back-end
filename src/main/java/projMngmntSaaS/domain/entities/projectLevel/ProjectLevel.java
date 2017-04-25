package projMngmntSaaS.domain.entities.projectLevel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * This provides common project level properties.
 */
@MappedSuperclass
public abstract class ProjectLevel extends ProjectLevelContents
{
    @Column(nullable = false)
    protected String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<ProjectLevelUpdate> archivedUpdates = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProjectLevelUpdate> getArchivedUpdates() {
        return archivedUpdates;
    }
}
