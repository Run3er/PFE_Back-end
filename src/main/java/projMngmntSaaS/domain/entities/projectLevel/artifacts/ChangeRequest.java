package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * TODO: describe a change request & extend its properties.
 */
@Entity
public class ChangeRequest extends ProjectLevelArtifact<ChangeRequest>
{
    @Column(nullable = false)
    private String description;

    public ChangeRequest() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
