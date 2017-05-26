package projMngmntSaaS.domain.entities.projectLevel.fixedDetails.interfaces;

/**
 * {@link projMngmntSaaS.domain.entities.projectLevel.fixedDetails.ProjectDetails} exhaustive interface
 */
public interface IProjectDetails extends ISubProjectDetails
{
    String getMainContact();

    void setMainContact(String mainContact);

    String getSponsors();

    void setSponsors(String sponsors);

    String getFinalClient();

    void setFinalClient(String finalClient);

    String getHypotheses_constraints();

    void setHypotheses_constraints(String hypotheses_constraints);

    String getHistory_decisions();

    void setHistory_decisions(String history_decisions);
}
