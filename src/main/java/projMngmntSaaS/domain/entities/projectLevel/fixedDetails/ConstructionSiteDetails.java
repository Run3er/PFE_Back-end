package projMngmntSaaS.domain.entities.projectLevel.fixedDetails;

import projMngmntSaaS.domain.entities.projectLevel.fixedDetails.interfaces.IConstructionSiteDetails;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;

/**
 * This provides {@link projMngmntSaaS.domain.entities.projectLevel.ConstructionSite} fixed properties.
 */
@MappedSuperclass
@Embeddable
public class ConstructionSiteDetails extends ProjectLevelDetails implements IConstructionSiteDetails
{
}
