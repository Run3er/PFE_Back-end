package projMngmntSaaS.domain.entities.projectLevel;

import projMngmntSaaS.domain.utils.UuidIdentifiable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * This provides common project level properties.
 */
@MappedSuperclass
public abstract class ProjectLevel extends ProjectLevelContents implements UuidIdentifiable
{
    @Column(nullable = false)
    protected String name;

    protected String goal;

    @Column(nullable = false)
    protected Date startDate;

    @Column(nullable = false)
    protected Date endDate;

    protected String comment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<ProjectLevelUpdate> archivedUpdates = new HashSet<>();

    @Override
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Set<ProjectLevelUpdate> getArchivedUpdates() {
        return archivedUpdates;
    }
}
