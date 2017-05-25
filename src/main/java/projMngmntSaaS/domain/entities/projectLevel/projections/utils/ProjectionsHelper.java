package projMngmntSaaS.domain.entities.projectLevel.projections.utils;

import projMngmntSaaS.domain.entities.enums.ActionStatus;
import projMngmntSaaS.domain.entities.enums.RiskStatus;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Action;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Risk;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Todo;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Helper methods to overcome the limitations of {@link org.springframework.beans.factory.annotation.Value} SPel
 * expressions (mainly the impossibility of using streams).
 */
public class ProjectionsHelper
{
    public static BigDecimal getBudgetConsumedPercentage(ProjectLevel pl) {
        if (pl.getBudgetToConsume().equals(BigDecimal.ZERO)) {
            return null;
        }
        // else budgetToConsume is > 0
        BigDecimal budgetTotal = pl.getBudgetToConsume().add(pl.getBudgetConsumed());
        return pl.getBudgetConsumed().multiply(new BigDecimal(100)).divide(budgetTotal, BigDecimal.ROUND_CEILING);
    }

    public static BigDecimal getBudgetPrevisionGapPercentage(ProjectLevel pl) {
        return pl.getBudgetInitial().compareTo(BigDecimal.ZERO) == 0 ?
                null :
                pl.getBudgetConsumed().add(pl.getBudgetToConsume()).subtract(pl.getBudgetInitial()).multiply(new BigDecimal(100)).divide(pl.getBudgetInitial(), BigDecimal.ROUND_CEILING);
    }

    public static BigDecimal getChargeConsumedPercentage(ProjectLevel pl) {
        BigDecimal chargeToConsume = new BigDecimal(
                pl.getTodos()
                        .stream()
                        .mapToInt(Todo::getCharge)
                        .sum()
        );
        BigDecimal chargeTotal = pl.getChargeConsumed().add(chargeToConsume);
        return chargeTotal.compareTo(BigDecimal.ZERO) == 0 ?
                BigDecimal.ZERO :
                pl.getChargeConsumed().multiply(new BigDecimal(100)).divide(chargeTotal, BigDecimal.ROUND_CEILING);
    }

    public static BigDecimal getChargePrevisionGapPercentage(ProjectLevel pl) {
        BigDecimal chargeToConsume = new BigDecimal(
                pl.getTodos()
                        .stream()
                        .mapToInt(Todo::getCharge)
                        .sum()
        );
        return pl.getChargePrevision().compareTo(BigDecimal.ZERO) == 0 ?
                null :
                pl.getChargeConsumed().add(chargeToConsume).subtract(pl.getChargePrevision()).multiply(new BigDecimal(100)).divide(pl.getChargePrevision(), BigDecimal.ROUND_CEILING);
    }

    public static Collection<Map<String, String>> getProjectLevelsAdvancement(Set<ProjectLevel> projectLevels) {
        return projectLevels.stream()
                .map(sp -> {
                    HashMap<String, String> sps = new HashMap<>();
                    sps.put("id", sp.getId() + "");
                    sps.put("name", sp.getName());
                    sps.put("advancement", sp.getAdvancement() + "");
                    return sps;
                })
                .collect(Collectors.toList());
    }

    public static Map<Boolean, Long> getActionsOngoingInTimeOrNotCount(Set<Action> actions) {
        return actions
                .stream()
                .filter(action -> action.getStatus().equals(ActionStatus.ONGOING))
                .map(action -> (action.getClosurePlannedDate().getTime() - new Date().getTime()) <= 0)
                .collect(groupingBy(Function.identity(), counting()));
    }

    public static Map<RiskStatus, Long> getRiskStatusesCount(Set<Risk> risks) {
        return risks
                .stream()
                .collect(groupingBy(Risk::getStatus, counting()));
    }
}
