package projMngmntSaaS.domain.entities.projectLevel.projections.utils;

import projMngmntSaaS.domain.entities.enums.ActionStatus;
import projMngmntSaaS.domain.entities.enums.RiskStatus;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Action;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Risk;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Todo;

import java.math.BigInteger;
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
    public static BigInteger getBudgetConsumedPercentage(ProjectLevel pl) {
        if (pl.getBudgetToConsume().compareTo(BigInteger.ZERO) == 0) {
            return null;
        }
        // else budgetToConsume is > 0
        BigInteger budgetTotal = pl.getBudgetToConsume().add(pl.getBudgetConsumed());
        return pl.getBudgetConsumed().multiply(new BigInteger("100")).divide(budgetTotal);
    }

    public static BigInteger getBudgetPrevisionGapPercentage(ProjectLevel pl) {
        return pl.getBudgetInitial().compareTo(BigInteger.ZERO) == 0 ?
                null :
                pl.getBudgetConsumed().add(pl.getBudgetToConsume()).subtract(pl.getBudgetInitial()).multiply(new BigInteger("100")).divide((pl.getBudgetInitial()));
    }

    public static BigInteger getChargeConsumedPercentage(ProjectLevel pl) {
        BigInteger chargeToConsume = new BigInteger(
                pl.getTodos()
                        .stream()
                        .mapToInt(Todo::getCharge)
                        .sum() + ""
        );
        BigInteger chargeTotal = pl.getChargeConsumed().add(chargeToConsume);
        return chargeTotal.compareTo(BigInteger.ZERO) == 0 ?
                BigInteger.ZERO :
                pl.getChargeConsumed().multiply(new BigInteger("100")).divide((chargeTotal));
    }

    public static BigInteger getChargePrevisionGapPercentage(ProjectLevel pl) {
        BigInteger chargeToConsume = new BigInteger(
                pl.getTodos()
                        .stream()
                        .mapToInt(Todo::getCharge)
                        .sum() + ""
        );
        return pl.getChargePrevision().compareTo(BigInteger.ZERO) == 0 ?
                null :
                pl.getChargeConsumed().add(chargeToConsume).subtract(pl.getChargePrevision()).multiply(new BigInteger("100")).divide((pl.getChargePrevision()));
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
