package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * A milestone is akin to a soft deadline. it has characterized by a name and a due date.
 */
@Entity
public class Milestone extends ProjectLevelArtifact
{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Date dueDate;

    public Milestone() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
