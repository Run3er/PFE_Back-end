package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import projMngmntSaaS.domain.entities.enums.ResourceType;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * A resource is an asset pertaining to a project level, which is used at some phase of its execution.
 */
@Entity
public class Resource extends ProjectLevelArtifact<Resource>
{
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private ResourceType type;

    public Resource() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }
}
