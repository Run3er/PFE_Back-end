package projMngmntSaaS.domain.entities.projectLevel.fixedDetails;

import projMngmntSaaS.domain.entities.projectLevel.fixedDetails.interfaces.ISubProjectDetails;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

/**
 * This provides {@link projMngmntSaaS.domain.entities.projectLevel.SubProject} fixed properties.
 */
@MappedSuperclass
@Embeddable
public class SubProjectDetails extends ConstructionSiteDetails implements ISubProjectDetails
{
}
