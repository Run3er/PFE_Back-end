package projMngmntSaaS.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;
import projMngmntSaaS.api.controllers.utils.ResourceRecursiveRetreiver;
import projMngmntSaaS.domain.entities.enums.ActionStatus;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Action;
import projMngmntSaaS.repositories.ConstructionSiteRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * API controller for retrieving pertinent statistics of resource collections ad-hoc.
 */
@RepositoryRestController
@RequestMapping(method = GET)
public class ResourceStatController
{
    private final ResourceRecursiveRetreiver resourceRecursiveRetreiver;
    private final ConstructionSiteRepository constructionSiteRepository;

    @Autowired
    public ResourceStatController(ResourceRecursiveRetreiver resourceRecursiveRetreiver, ConstructionSiteRepository constructionSiteRepository) {
        this.resourceRecursiveRetreiver = resourceRecursiveRetreiver;
        this.constructionSiteRepository = constructionSiteRepository;
    }

    // Seems Spring Data REST @RepositoryRestController can't work on overriding behavior with regex path,
    // since conflicts are raised instead. Thus, each path literal is specifies explicitly in the mappings
    @RequestMapping(value = {
            "**/projects/{parentResourceId}/actions/stats",
            "**/subProjects/{parentResourceId}/actions/stats",
            "**/constructionSites/{parentResourceId}/actions/stats"
    })
    public ResponseEntity<?> getActionsStats(@PathVariable UUID parentResourceId, HttpServletRequest request) {
        String restOfTheUri = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String[] UriParts = restOfTheUri.split("/");
        String parentResourcePath = UriParts[UriParts.length - 4];

        String subResourcePath = "actions";
        List<Action> actions;
        if ("projects".equals(parentResourcePath) || "subProjects".equals(parentResourcePath)) {
            actions = (List<Action>) resourceRecursiveRetreiver.retrieve(parentResourcePath, parentResourceId, subResourcePath).get(subResourcePath);
        }
        else if ("constructionSites".equals(parentResourcePath)) {
            // Set<> to List<>
            actions = new ArrayList<>(constructionSiteRepository.findOne(parentResourceId).getActions());
        }
        else {
            throw new IllegalArgumentException("Parent resource unhandled. Check against controller request mapping values.");
        }

        int actionsOngoingCount = 0;
        int actionsOngoingInTimeCount = 0;
        int actionsOngoingLateCount = 0;
        int actionsStandbyCount = 0;
        for (Action action : actions) {
            if (action.getStatus().equals(ActionStatus.ONGOING)) {
                actionsOngoingCount++;
                if (action.getClosurePlannedDate().after(new Date())) {
                    actionsOngoingInTimeCount++;
                }
                else {
                    actionsOngoingLateCount++;
                }
            }
            else if (action.getStatus().equals(ActionStatus.STANDBY)) {
                actionsStandbyCount++;
            }
        }

        Map<String, Integer> stats = new HashMap<>();
        stats.put("actionsOngoingCount", actionsOngoingCount);
        stats.put("actionsOngoingInTimeCount", actionsOngoingInTimeCount);
        stats.put("actionsOngoingLateCount", actionsOngoingLateCount);
        stats.put("actionsStandbyCount", actionsStandbyCount);

        return new ResponseEntity<>(stats,  HttpStatus.OK);
    }
}
