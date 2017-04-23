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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Resource resource = (Resource) o;

        if (name != null ? !name.equals(resource.name) : resource.name != null) return false;
        return type == resource.type;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
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
