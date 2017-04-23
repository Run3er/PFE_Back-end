package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * TODO: describe a pending issue & extend its properties.
 */
@Entity
public class PendingIssue extends ProjectLevelArtifact<PendingIssue>
{
    @Column(nullable = false)
    private String description;

    public PendingIssue() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
