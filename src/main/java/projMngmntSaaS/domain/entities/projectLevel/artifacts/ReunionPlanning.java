package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import projMngmntSaaS.domain.entities.enums.ReunionPlanningStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * TODO: description
 */
@Entity
public class ReunionPlanning extends ProjectLevelArtifact<ReunionPlanning>
{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private ReunionPlanningStatus status;

    private String location;

    private Date date;

    public ReunionPlanning() {
        // no-arg constructor for ORM (due to reflection use)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ReunionPlanning that = (ReunionPlanning) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        return date != null ? date.equals(that.date) : that.date == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReunionPlanningStatus getStatus() {
        return status;
    }

    public void setStatus(ReunionPlanningStatus status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public ReunionPlanning shallowClone() {
        ReunionPlanning clone = new ReunionPlanning();

        shallowCloneRootsInto(clone);
        clone.name = name;
        clone.status = status;
        clone.location = location;
        clone.date = date;

        return clone;
    }
}
