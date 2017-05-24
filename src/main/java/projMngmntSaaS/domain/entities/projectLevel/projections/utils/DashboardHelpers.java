package projMngmntSaaS.domain.entities.projectLevel.projections.utils;

import projMngmntSaaS.domain.entities.enums.ActionStatus;
import projMngmntSaaS.domain.entities.enums.RiskStatus;
import projMngmntSaaS.domain.entities.projectLevel.ProjectLevel;
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
public class DashboardHelpers
{
    public static BigDecimal getBudgetConsumedPercentage(BigDecimal budgetConsumed, BigDecimal budgetToConsume) {
        BigDecimal budgetTotal = budgetToConsume.add(budgetConsumed);
        return budgetTotal.compareTo(BigDecimal.ZERO) == 0 ?
                BigDecimal.ZERO :
                budgetConsumed.multiply(new BigDecimal(100)).divide(budgetTotal, BigDecimal.ROUND_CEILING);
    }

    public static BigDecimal getBudgetPrevisionGapPercentage(BigDecimal budgetInitial, BigDecimal budgetConsumed, BigDecimal budgetToConsume) {
        return budgetInitial.compareTo(BigDecimal.ZERO) == 0 ?
                BigDecimal.ZERO :
                budgetConsumed.add(budgetToConsume).subtract(budgetInitial).multiply(new BigDecimal(100)).divide(budgetInitial, BigDecimal.ROUND_CEILING);
    }

    public static BigDecimal getChargeConsumedPercentage(BigDecimal chargeConsumed, Set<Todo> todos) {
        BigDecimal chargeToConsume = new BigDecimal(
                todos.stream().mapToInt(Todo::getCharge).sum()
        );
        BigDecimal chargeTotal = chargeConsumed.add(chargeToConsume);
        return chargeTotal.compareTo(BigDecimal.ZERO) == 0 ?
                BigDecimal.ZERO :
                chargeConsumed.multiply(new BigDecimal(100)).divide(chargeTotal, BigDecimal.ROUND_CEILING);
    }

    public static BigDecimal getChargePrevisionGapPercentage(BigDecimal chargePrevision, BigDecimal chargeConsumed, Set<Todo> todos) {
        BigDecimal chargeToConsume = new BigDecimal(
                todos.stream().mapToInt(Todo::getCharge).sum()
        );
        return chargePrevision.compareTo(BigDecimal.ZERO) == 0 ?
                BigDecimal.ZERO :
                chargeConsumed.add(chargeToConsume).subtract(chargePrevision).multiply(new BigDecimal(100)).divide(chargePrevision, BigDecimal.ROUND_CEILING);
    }

    public static Collection<Map<String, String>> getProjectLevelsAdvancement(Set<ProjectLevel> projectLevels) {
        return projectLevels
                .stream()
                .map(sp -> {
                    HashMap<String, String> sps = new HashMap<>();
                    sps.put("id", sp.getId() + "");
                    sps.put("name", sp.getName());
                    sps.put("advancement", sp.getArchivableContent().getAdvancement() + "");
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
