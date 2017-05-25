package projMngmntSaaS.domain.entities.projectLevel.fixedDetails.interfaces;

import java.math.BigDecimal;
import java.util.Date;

/**
 * {@link projMngmntSaaS.domain.entities.projectLevel.fixedDetails.ProjectLevelDetails} exhaustive interface.
 */
public interface IProjectLevelDetails
{
    String getName();

    void setName(String name);

    String getGoal();

    void setGoal(String goal);

    Date getStartDate();

    void setStartDate(Date startDate);

    Date getEndDate();

    void setEndDate(Date endDate);

    String getComment();

    void setComment(String comment);

    BigDecimal getBudgetInitial();

    void setBudgetInitial(BigDecimal budgetInitial);

    BigDecimal getChargePrevision();

    void setChargePrevision(BigDecimal chargePrevision);
}
