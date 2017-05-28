package projMngmntSaaS.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;
import projMngmntSaaS.api.controllers.utils.ProjectFullUpdateArchiver;
import projMngmntSaaS.api.controllers.utils.ResourceAppender;
import projMngmntSaaS.api.controllers.utils.ResourceDeleter;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.repositories.ProjectRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * API controller for customizing Spring Data REST endpoints.
 */
@RepositoryRestController
public class NestedResourceExtensionController
{
    private final ProjectRepository projectRepository;
    private final ProjectFullUpdateArchiver projectFullUpdateArchiver;
    private final ResourceAppender resourceAppender;
    private final ResourceDeleter resourceDeleter;

    @Autowired
    public NestedResourceExtensionController(ProjectRepository projectRepository, ProjectFullUpdateArchiver projectFullUpdateArchiver,
                                             ResourceAppender resourceAppender, ResourceDeleter resourceDeleter) {
        this.projectRepository = projectRepository;
        this.projectFullUpdateArchiver = projectFullUpdateArchiver;
        this.resourceAppender = resourceAppender;
        this.resourceDeleter = resourceDeleter;
    }

    // Seems Spring Data REST @RepositoryRestController can't work on overriding behavior with regex path,
    // since conflicts are raised instead. Thus, each path literal is specifies explicitly in the mappings
    @RequestMapping(method = POST, consumes = "application/json", value = {
            "**/projects/{parentResourceId}/subProjects",
            "**/projects/{parentResourceId}/constructionSites",
            "**/subProjects/{parentResourceId}/constructionSites",

            "**/projects/{parentResourceId}/actions",
            "**/projects/{parentResourceId}/risks",
            "**/projects/{parentResourceId}/changeRequests",
            "**/projects/{parentResourceId}/pendingIssues",
            "**/projects/{parentResourceId}/resources",
            "**/projects/{parentResourceId}/documents",
            "**/projects/{parentResourceId}/milestones",
            "**/projects/{parentResourceId}/todos",
            "**/projects/{parentResourceId}/reunionPlannings",
            "**/projects/{parentResourceId}/communicationPlans",
            "**/projects/{parentResourceId}/humanResources",
            "**/projects/{parentResourceId}/writeups",

            "**/subProjects/{parentResourceId}/actions",
            "**/subProjects/{parentResourceId}/risks",
            "**/subProjects/{parentResourceId}/changeRequests",
            "**/subProjects/{parentResourceId}/pendingIssues",
            "**/subProjects/{parentResourceId}/resources",
            "**/subProjects/{parentResourceId}/documents",
            "**/subProjects/{parentResourceId}/milestones",
            "**/subProjects/{parentResourceId}/todos",
            "**/subProjects/{parentResourceId}/reunionPlannings",
            "**/subProjects/{parentResourceId}/communicationPlans",
            "**/subProjects/{parentResourceId}/humanResources",
            "**/subProjects/{parentResourceId}/writeups",

            "**/constructionSites/{parentResourceId}/actions",
            "**/constructionSites/{parentResourceId}/risks",
            "**/constructionSites/{parentResourceId}/changeRequests",
            "**/constructionSites/{parentResourceId}/pendingIssues",
            "**/constructionSites/{parentResourceId}/resources",
            "**/constructionSites/{parentResourceId}/documents",
            "**/constructionSites/{parentResourceId}/milestones",
            "**/constructionSites/{parentResourceId}/todos",
            "**/constructionSites/{parentResourceId}/reunionPlannings",
            "**/constructionSites/{parentResourceId}/communicationPlans",
            "**/constructionSites/{parentResourceId}/humanResources",
            "**/constructionSites/{parentResourceId}/writeups"
    })
    public ResponseEntity<?> appendSubResources(@PathVariable UUID parentResourceId, HttpServletRequest request,
                                                @RequestBody Collection<Object> subResources) {
        String restOfTheUri = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String[] UriParts = restOfTheUri.split("/");
        String subResourcePath = UriParts[UriParts.length - 1];
        String parentResourcePath = UriParts[UriParts.length - 3];

        List<UUID> appendedUuids;
        try {
            appendedUuids = resourceAppender.append(parentResourcePath, parentResourceId, subResourcePath, subResources);
            return new ResponseEntity<>(appendedUuids,  HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = POST, value = "/**/projects/{projectId}/archivedUpdates")
    public ResponseEntity<?> archivePendingUpdate(@PathVariable UUID projectId) {
        Project project = projectRepository.findOne(projectId);

        if (project == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        projectFullUpdateArchiver.archive(project, new Date());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Seems Spring Data REST @RepositoryRestController can't work on overriding behavior with regex path,
    // since conflicts are raised instead. Thus, each path literal is specifies explicitly in the mappings
    @RequestMapping(method = DELETE, value = {
            "**/projects/{parentResourceId}/subProjects/{subResourceId}",
            "**/projects/{parentResourceId}/constructionSites/{subResourceId}",
            "**/subProjects/{parentResourceId}/constructionSites/{subResourceId}",

            "**/projects/{parentResourceId}/actions/{subResourceId}",
            "**/projects/{parentResourceId}/risks/{subResourceId}",
            "**/projects/{parentResourceId}/changeRequests/{subResourceId}",
            "**/projects/{parentResourceId}/pendingIssues/{subResourceId}",
            "**/projects/{parentResourceId}/resources/{subResourceId}",
            "**/projects/{parentResourceId}/documents/{subResourceId}",
            "**/projects/{parentResourceId}/milestones/{subResourceId}",
            "**/projects/{parentResourceId}/todos/{subResourceId}",
            "**/projects/{parentResourceId}/reunionPlannings/{subResourceId}",
            "**/projects/{parentResourceId}/communicationPlans/{subResourceId}",
            "**/projects/{parentResourceId}/humanResources/{subResourceId}",
            "**/projects/{parentResourceId}/writeups/{subResourceId}",

            "**/subProjects/{parentResourceId}/actions/{subResourceId}",
            "**/subProjects/{parentResourceId}/risks/{subResourceId}",
            "**/subProjects/{parentResourceId}/changeRequests/{subResourceId}",
            "**/subProjects/{parentResourceId}/pendingIssues/{subResourceId}",
            "**/subProjects/{parentResourceId}/resources/{subResourceId}",
            "**/subProjects/{parentResourceId}/documents/{subResourceId}",
            "**/subProjects/{parentResourceId}/milestones/{subResourceId}",
            "**/subProjects/{parentResourceId}/todos/{subResourceId}",
            "**/subProjects/{parentResourceId}/reunionPlannings/{subResourceId}",
            "**/subProjects/{parentResourceId}/communicationPlans/{subResourceId}",
            "**/subProjects/{parentResourceId}/humanResources/{subResourceId}",
            "**/subProjects/{parentResourceId}/writeups/{subResourceId}",

            "**/constructionSites/{parentResourceId}/actions/{subResourceId}",
            "**/constructionSites/{parentResourceId}/risks/{subResourceId}",
            "**/constructionSites/{parentResourceId}/changeRequests/{subResourceId}",
            "**/constructionSites/{parentResourceId}/pendingIssues/{subResourceId}",
            "**/constructionSites/{parentResourceId}/resources/{subResourceId}",
            "**/constructionSites/{parentResourceId}/documents/{subResourceId}",
            "**/constructionSites/{parentResourceId}/milestones/{subResourceId}",
            "**/constructionSites/{parentResourceId}/todos/{subResourceId}",
            "**/constructionSites/{parentResourceId}/reunionPlannings/{subResourceId}",
            "**/constructionSites/{parentResourceId}/communicationPlans/{subResourceId}",
            "**/constructionSites/{parentResourceId}/humanResources/{subResourceId}",
            "**/constructionSites/{parentResourceId}/writeups/{subResourceId}"
    })
    public ResponseEntity<?> deleteSubResources(@PathVariable UUID parentResourceId, @PathVariable UUID subResourceId,
                                                HttpServletRequest request) {
        String restOfTheUri = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String[] UriParts = restOfTheUri.split("/");
        String subResourcePath = UriParts[UriParts.length - 2];
        String parentResourcePath = UriParts[UriParts.length - 4];

        try {
            resourceDeleter.delete(parentResourcePath, parentResourceId, subResourcePath, subResourceId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }
}
