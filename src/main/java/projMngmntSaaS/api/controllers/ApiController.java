package projMngmntSaaS.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
public class ApiController
{
    private final ProjectRepository projectRepository;
    private final ProjectFullUpdateArchiver projectFullUpdateArchiver;
    private final ResourceAppender resourceAppender;
    private final ResourceDeleter resourceDeleter;

    @Autowired
    public ApiController(ProjectRepository projectRepository, ProjectFullUpdateArchiver projectFullUpdateArchiver,
                         ResourceAppender resourceAppender, ResourceDeleter resourceDeleter) {
        this.projectRepository = projectRepository;
        this.projectFullUpdateArchiver = projectFullUpdateArchiver;
        this.resourceAppender = resourceAppender;
        this.resourceDeleter = resourceDeleter;
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
    @RequestMapping(method = POST, consumes = "application/json", value = {
            "**/entities/{parentResourceId}/projects",
            "**/projects/{parentResourceId}/subProjects",
            "**/subProjects/{parentResourceId}/constructionSites",

            "**/projects/{parentResourceId}/actions",
            "**/projects/{parentResourceId}/risks",
            "**/projects/{parentResourceId}/changeRequests",
            "**/projects/{parentResourceId}/pendingIssues",
            "**/projects/{parentResourceId}/resources",
            "**/projects/{parentResourceId}/documents",
            "**/projects/{parentResourceId}/milestones",

            "**/subProjects/{parentResourceId}/actions",
            "**/subProjects/{parentResourceId}/risks",
            "**/subProjects/{parentResourceId}/changeRequests",
            "**/subProjects/{parentResourceId}/pendingIssues",
            "**/subProjects/{parentResourceId}/resources",
            "**/subProjects/{parentResourceId}/documents",
            "**/subProjects/{parentResourceId}/milestones",

            "**/constructionSites/{parentResourceId}/actions",
            "**/constructionSites/{parentResourceId}/risks",
            "**/constructionSites/{parentResourceId}/changeRequests",
            "**/constructionSites/{parentResourceId}/pendingIssues",
            "**/constructionSites/{parentResourceId}/resources",
            "**/constructionSites/{parentResourceId}/documents",
            "**/constructionSites/{parentResourceId}/milestones"
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

    // Seems Spring Data REST @RepositoryRestController can't work on overriding behavior with regex path,
    // since conflicts are raised instead. Thus, each path literal is specifies explicitly in the mappings
    @RequestMapping(method = DELETE, value = {
            "**/entities/{parentResourceId}/projects/{subResourceId}",
            "**/projects/{parentResourceId}/subProjects/{subResourceId}",
            "**/subProjects/{parentResourceId}/constructionSites/{subResourceId}",

            "**/projects/{parentResourceId}/actions/{subResourceId}",
            "**/projects/{parentResourceId}/risks/{subResourceId}",
            "**/projects/{parentResourceId}/changeRequests/{subResourceId}",
            "**/projects/{parentResourceId}/pendingIssues/{subResourceId}",
            "**/projects/{parentResourceId}/resources/{subResourceId}",
            "**/projects/{parentResourceId}/documents/{subResourceId}",
            "**/projects/{parentResourceId}/milestones/{subResourceId}",

            "**/subProjects/{parentResourceId}/actions/{subResourceId}",
            "**/subProjects/{parentResourceId}/risks/{subResourceId}",
            "**/subProjects/{parentResourceId}/changeRequests/{subResourceId}",
            "**/subProjects/{parentResourceId}/pendingIssues/{subResourceId}",
            "**/subProjects/{parentResourceId}/resources/{subResourceId}",
            "**/subProjects/{parentResourceId}/documents/{subResourceId}",
            "**/subProjects/{parentResourceId}/milestones/{subResourceId}",

            "**/constructionSites/{parentResourceId}/actions/{subResourceId}",
            "**/constructionSites/{parentResourceId}/risks/{subResourceId}",
            "**/constructionSites/{parentResourceId}/changeRequests/{subResourceId}",
            "**/constructionSites/{parentResourceId}/pendingIssues/{subResourceId}",
            "**/constructionSites/{parentResourceId}/resources/{subResourceId}",
            "**/constructionSites/{parentResourceId}/documents/{subResourceId}",
            "**/constructionSites/{parentResourceId}/milestones/{subResourceId}"
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
