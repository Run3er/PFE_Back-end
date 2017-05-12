package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import projMngmntSaaS.domain.entities.enums.ResourceType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * TODO: description
 */
@Entity
public class CommunicationPlan extends ProjectLevelArtifact<CommunicationPlan>
{
    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    private Resource supervisor;

    public CommunicationPlan() {
        // no-arg constructor for ORM (due to reflection use)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CommunicationPlan that = (CommunicationPlan) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return supervisor != null ? supervisor.equals(that.supervisor) : that.supervisor == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (supervisor != null ? supervisor.hashCode() : 0);
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resource getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Resource supervisor) {
        if (supervisor.getType() != ResourceType.HUMAN) {
            throw new IllegalArgumentException("Supervisor can only be a resource of type " + ResourceType.HUMAN + ".");
        }
        this.supervisor = supervisor;
    }

    @Override
    public CommunicationPlan shallowClone() {
        CommunicationPlan clone = new CommunicationPlan();

        shallowCloneRootsInto(clone);
        clone.name = name;
        clone.supervisor = supervisor;

        return clone;
    }
}
