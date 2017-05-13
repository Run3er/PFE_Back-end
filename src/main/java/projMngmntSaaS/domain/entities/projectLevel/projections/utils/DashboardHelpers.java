package projMngmntSaaS.domain.entities.projectLevel.projections.utils;

import projMngmntSaaS.domain.entities.enums.ActionStatus;
import projMngmntSaaS.domain.entities.enums.RiskStatus;
import projMngmntSaaS.domain.entities.projectLevel.ProjectLevel;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Action;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Risk;

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
