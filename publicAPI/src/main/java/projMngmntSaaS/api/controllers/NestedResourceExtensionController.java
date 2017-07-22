package projMngmntSaaS.api.controllers;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import projMngmntSaaS.api.controllers.utils.ProjectFullUpdateArchiver;
import projMngmntSaaS.api.controllers.utils.ResourceAppender;
import projMngmntSaaS.api.controllers.utils.ResourceDeleter;
import projMngmntSaaS.api.controllers.utils.ResourceRecursiveRetreiver;
import projMngmntSaaS.domain.entities.projectLevel.Project;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.Document;
import projMngmntSaaS.repositories.DocumentRepository;
import projMngmntSaaS.repositories.ProjectRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * API controller for customizing Spring Data REST endpoints.
 */
@RepositoryRestController
public class NestedResourceExtensionController
{
    private final ProjectRepository projectRepository;
    private final DocumentRepository documentRepository;
    private final ProjectFullUpdateArchiver projectFullUpdateArchiver;
    private final ResourceAppender resourceAppender;
    private final ResourceDeleter resourceDeleter;
    private final ResourceRecursiveRetreiver resourceRecursiveRetreiver;
    @Value("${management.address}")
    private String hostAddress;
    @Value("${server.port}")
    private String hostPort;

    @Autowired
    public NestedResourceExtensionController(ProjectRepository projectRepository, DocumentRepository documentRepository, ProjectFullUpdateArchiver projectFullUpdateArchiver, ResourceAppender resourceAppender, ResourceDeleter resourceDeleter, ResourceRecursiveRetreiver resourceRecursiveRetreiver) {
        this.projectRepository = projectRepository;
        this.documentRepository = documentRepository;
        this.projectFullUpdateArchiver = projectFullUpdateArchiver;
        this.resourceAppender = resourceAppender;
        this.resourceDeleter = resourceDeleter;
        this.resourceRecursiveRetreiver = resourceRecursiveRetreiver;
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
//            "**/projects/{parentResourceId}/documents",
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
//            "**/subProjects/{parentResourceId}/documents",
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
//            "**/constructionSites/{parentResourceId}/documents",
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
            return new ResponseEntity<>(appendedUuids, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
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

    // Seems Spring Data REST @RepositoryRestController can't work on overriding behavior with regex path,
    // since conflicts are raised instead. Thus, each path literal is specifies explicitly in the mappings
    @RequestMapping(method = GET, value = {
            "**/projects/{parentResourceId}/constructionSites",

            "**/projects/{parentResourceId}/actions",
            "**/projects/{parentResourceId}/risks",
            "**/projects/{parentResourceId}/changeRequests",
            "**/projects/{parentResourceId}/pendingIssues",
            "**/projects/{parentResourceId}/resources",
            "**/projects/{parentResourceId}/todos",
            "**/projects/{parentResourceId}/humanResources",

            "**/subProjects/{parentResourceId}/actions",
            "**/subProjects/{parentResourceId}/risks",
            "**/subProjects/{parentResourceId}/changeRequests",
            "**/subProjects/{parentResourceId}/pendingIssues",
            "**/subProjects/{parentResourceId}/resources",
            "**/subProjects/{parentResourceId}/todos",
            "**/subProjects/{parentResourceId}/humanResources"
    })
    public ResponseEntity<?> getAllSubResources(@PathVariable UUID parentResourceId, HttpServletRequest request) {
        String restOfTheUri = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String[] UriParts = restOfTheUri.split("/");
        String subResourcePath = UriParts[UriParts.length - 1];
        String parentResourcePath = UriParts[UriParts.length - 3];

        Map<String, Object> response = new HashMap<>();
        Map<String, Object> recursivelyRetrievedResources;
        try {
            recursivelyRetrievedResources = resourceRecursiveRetreiver.retrieve(parentResourcePath, parentResourceId, subResourcePath);
            // Prepare API response
            // _embedded
            response.put("_embedded", recursivelyRetrievedResources);
            // _links
            Map<String, Object> link = new HashMap<>();
            Map<String, Object> self = new HashMap<>();
            self.put("href", "http://" + hostAddress + ":" + hostPort + "/" + parentResourcePath + "/" +
                    parentResourceId + "/" + subResourcePath);
            link.put("self", self);
            response.put("_links", link);

            return new ResponseEntity<>(response, HttpStatus.OK);
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

    @RequestMapping(method = POST, value = {
            "**/constructionSites/{parentResourceId}/documents",
            "**/projects/{parentResourceId}/documents",
            "**/subProjects/{parentResourceId}/documents"
    })
    public ResponseEntity<?> documentUpload(@RequestParam("file") MultipartFile file,
                                            @PathVariable UUID parentResourceId, HttpServletRequest request) {
        String restOfTheUri = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        String[] UriParts = restOfTheUri.split("/");
        String subResourcePath = UriParts[UriParts.length - 1]; // 'documents'
        String parentResourcePath = UriParts[UriParts.length - 3];

        // Persist file on disk
        String filePath = System.getProperty("user.dir") + File.separator + "pm-uploads" + File.separator + parentResourceId + File.separator;
        String nonce = null;
        int tries = 0;
        while (tries < 5) {
            tries++;
            nonce = UUID.randomUUID().toString();
            try {
                File targetFile = new File(filePath + nonce);
                File parent = targetFile.getParentFile();
                // Create file path recursively
                if (!targetFile.getParentFile().exists() && !parent.mkdirs()) {
                    throw new IllegalStateException("Couldn't create dir: " + parent);
                }
                file.transferTo(targetFile);
                break;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (tries >= 5) {
            Map<String, String> response = new HashMap<>();
            response.put("Error", "Uploaded file persistence failed.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Persist on database
        Document document = new Document();
        document.setName(file.getOriginalFilename());
        document.setOsId(nonce);
        document.setContentType(file.getContentType());
        Collection<Document> subResources = new ArrayList<>();
        subResources.add(document);
        List<UUID> appendedUuids;
        try {
            appendedUuids = resourceAppender.append(parentResourcePath, parentResourceId, subResourcePath, subResources);
            return new ResponseEntity<>(appendedUuids, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = GET, value = {
            "**/constructionSites/{parentResourceId}/documents/{subResourceId}",
            "**/projects/{parentResourceId}/documents/{subResourceId}",
            "**/subProjects/{parentResourceId}/documents/{subResourceId}"
    })
    public ResponseEntity<?> documentUpload(@PathVariable UUID subResourceId, HttpServletResponse response,
                                            @PathVariable UUID parentResourceId) {
        // Retrieve path from database
        Document document = documentRepository.getOne(subResourceId);
        if (document == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Retrieve file from disk
        String filePath = document.getOsId();
        File file = new File(System.getProperty("user.dir") + File.separator + "pm-uploads" + File.separator + parentResourceId + File.separator + filePath);
        try {
            IOUtils.copy(new FileInputStream(file), response.getOutputStream());
            response.setContentType(document.getContentType());
            response.setHeader("X-File-Name", document.getName());
            response.setHeader("Access-Control-Expose-Headers", "X-File-Name");
            response.flushBuffer();
        } catch (IOException ex) {
            System.out.println("Error writing file to output stream. Filename was '" + filePath + "'");
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
