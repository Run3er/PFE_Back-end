package projMngmntSaaS.domain.entities.projectLevel.fixedDetails;

import projMngmntSaaS.domain.entities.projectLevel.fixedDetails.interfaces.IProjectDetails;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

/**
 * This provides {@link projMngmntSaaS.domain.entities.projectLevel.Project} fixed properties.
 */
@MappedSuperclass
@Embeddable
public class ProjectDetails extends SubProjectDetails implements IProjectDetails
{
    private String mainContact;

    private String sponsors;

    private String finalClient;

    private String hypotheses_constraints;

    private String history_decisions;

    public String getMainContact() {
        return mainContact;
    }

    public void setMainContact(String mainContact) {
        this.mainContact = mainContact;
    }

    public String getSponsors() {
        return sponsors;
    }

    public void setSponsors(String sponsors) {
        this.sponsors = sponsors;
    }

    public String getFinalClient() {
        return finalClient;
    }

    public void setFinalClient(String finalClient) {
        this.finalClient = finalClient;
    }

    public String getHypotheses_constraints() {
        return hypotheses_constraints;
    }

    public void setHypotheses_constraints(String hypotheses_constraints) {
        this.hypotheses_constraints = hypotheses_constraints;
    }

    public String getHistory_decisions() {
        return history_decisions;
    }

    public void setHistory_decisions(String history_decisions) {
        this.history_decisions = history_decisions;
    }
}
