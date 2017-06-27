package projMngmntSaaS.api.controllers.utils;

import org.hibernate.SQLQuery;
import org.hibernate.type.PostgresUUIDType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import projMngmntSaaS.domain.entities.projectLevel.ConstructionSite;
import projMngmntSaaS.domain.entities.projectLevel.artifacts.ProjectLevelArtifact;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;


/**
 * Deletes sub-resources completely, by removing them from their parent resource first.
 */
@Component
@Scope("prototype")
public class ResourceRecursiveRetreiver
{
    private static final String URI_NESTED_RESRC_INVALID_MSG = "URI incorrect: Nested resource invalid.";
    private static final String URI_RESRC_INVALID_MSG = "URI incorrect: Hierarchy head resource invalid.";
    private static final List<String> recursivelyRetrivedArtifacts = Arrays.asList("constructionSites", "actions", "risks", "changeRequests", "pendingIssues", "resources", "todos", "humanResources");

    @PersistenceContext
    private EntityManager entityManager;



    @Transactional
    public Map<String, Object> retrieve(String parentResourcePath, UUID parentResrcId, String subResourcePath)
            throws IllegalArgumentException {
        if ("projects".equals(parentResourcePath) || "subProjects".equals(parentResourcePath)) {
            if (!recursivelyRetrivedArtifacts.contains(subResourcePath)) {
                throw new IllegalArgumentException(URI_NESTED_RESRC_INVALID_MSG);
            }

            // Stringify UUID
            String parentId = parentResrcId.toString().replace("-", "");
            // Prepare resource class name
            Class<?> resourceClass = null;
            if ("constructionSites".equals(subResourcePath)) {
                resourceClass = ConstructionSite.class;
            }
            else {
                String name = ProjectLevelArtifact.class.getName();
                   String[] split = name.split("\\.");
                String classSimpleName = subResourcePath.substring(0, 1).toUpperCase() + (
                        subResourcePath.length() == 1 ? "" : subResourcePath.substring(1, subResourcePath.length() - 1) );
                split[split.length - 1] = classSimpleName;
                String className = String.join(".", split);
                try {
                    resourceClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            // Prepare class SQL table name (camelCase to snake_case)
            String subResrcTableName = subResourcePath.replaceAll("([A-Z][a-z]*)", "_$1").toLowerCase();
            // Remove plural 's' suffix
            String subResrcPathSingle = subResrcTableName.substring(0, subResrcTableName.length() - 1);

            // TODO: decouple from PostgreSQL dialect (to be compatible w/mysql for exp.)
            String queryString;
            if ("constructionSites".equals(subResourcePath)) {
                queryString =
                        // JOIN project levels' ids w/ resource info
                        "SELECT * FROM ( " +
                                //Project
                                "SELECT construction_sites_id AS id_bis, null AS sub_project_id " +
                                "FROM project_construction_sites " +
                                "WHERE project_id = '" + parentId + "' " +
                                "UNION " +
                                //Project_SubProjects
                                "SELECT construction_sites_id AS id_bis, sub_project_id " +
                                "FROM sub_project_construction_sites " +
                                "WHERE sub_project_id IN ( " +
                                    "SELECT sub_projects_id " +
                                    "FROM project_sub_projects " +
                                    "WHERE project_id = '" + parentId + "' " +
                                ") " +
                        ") AS bis " +
                        "JOIN construction_site ON construction_site.id = bis.id_bis " +
                        "LEFT JOIN (SELECT name AS sub_project_name, id AS sub_project_id_bis FROM sub_project) AS sub_projects ON sub_project_id_bis = sub_project_id";
            }
            else {
                queryString = "projects".equals(parentResourcePath) ?
                        // JOIN project levels' ids w/ resource info
                        "SELECT * FROM ( " +
                            //Project
                            "SELECT " + subResrcTableName + "_id AS id_bis, null\\:\\:UUID AS sub_project_id, null\\:\\:UUID AS construction_site_id " +
                            "FROM project_" + subResrcTableName + " " +
                            "WHERE project_id = '" + parentId + "' " +
                            "UNION " +
                            //Project_ConstructionSites
                            "SELECT " + subResrcTableName + "_id AS id_bis, null\\:\\:UUID AS sub_project_id, construction_site_id " +
                            "FROM construction_site_" + subResrcTableName + " " +
                            "WHERE construction_site_id IN ( " +
                                "SELECT construction_sites_id " +
                                "FROM project_construction_sites " +
                                "WHERE project_id = '" + parentId + "' " +
                            ") " +
                            "UNION " +
                            //Project_SubProject
                            "SELECT " + subResrcTableName + "_id AS id_bis, sub_project_id, null\\:\\:UUID AS construction_site_id " +
                            "FROM sub_project_" + subResrcTableName + " " +
                            "WHERE sub_project_id IN ( " +
                                "SELECT sub_projects_id " +
                                "FROM project_sub_projects " +
                                "WHERE project_id = '" + parentId + "' " +
                            ") " +
                            "UNION " +
                            //Project_SubProjects_ConstructionSites
                            "SELECT " + subResrcTableName + "_id AS id_bis, sub_project_id, bisbis.construction_site_id " +
                            "FROM ( " +
                                "SELECT sub_project_id AS sub_project_id, construction_sites_id AS construction_site_id " +
                                "FROM sub_project_construction_sites " +
                                "WHERE sub_project_id IN ( " +
                                    "SELECT sub_projects_id " +
                                    "FROM project_sub_projects " +
                                    "WHERE project_id = '" + parentId + "' " +
                                ") " +
                            ") AS bisbis JOIN construction_site_" + subResrcTableName + " " +
                            "ON construction_site_" + subResrcTableName + ".construction_site_id = bisbis.construction_site_id " +
                        ") AS bis " +
                        "JOIN " + subResrcPathSingle + " ON " + subResrcPathSingle + ".id = bis.id_bis " +
                        "LEFT JOIN (SELECT name AS construction_site_name, id AS construction_site_id_bis FROM construction_site) AS construction_sites ON construction_site_id_bis = construction_site_id " +
                        "LEFT JOIN (SELECT name AS sub_project_name, id AS sub_project_id_bis FROM sub_project) AS sub_projects ON sub_project_id_bis = sub_project_id"
                        :
                        "SELECT * FROM ( " +
                            //SubProject
                            "SELECT " + subResrcTableName + "_id AS id_bis, null AS construction_site_id " +
                            "FROM sub_project_" + subResrcTableName + " " +
                            "WHERE sub_project_id = '" + parentId + "' " +
                            "UNION " +
                            //SubProject_ConstructionSites
                            "SELECT " + subResrcTableName + "_id AS id_bis, construction_site_id " +
                            "FROM construction_site_" + subResrcTableName + " " +
                            "WHERE construction_site_id IN ( " +
                                "SELECT construction_sites_id " +
                                "FROM sub_project_construction_sites " +
                                "WHERE sub_project_id = '" + parentId + "' " +
                            ") " +
                        ") AS bis " +
                        "JOIN " + subResrcPathSingle + " ON " + subResrcPathSingle + ".id = bis.id_bis " +
                        "LEFT JOIN (SELECT name AS construction_site_name, id AS construction_site_id_bis FROM construction_site) AS construction_sites ON construction_site_id_bis = construction_site_id";
            }


            // Map SQL result into objects
            Query nativeQuery = entityManager.createNativeQuery(queryString);
            SQLQuery query = nativeQuery.unwrap(SQLQuery.class);
            query.addEntity(resourceClass);
            if ("constructionSites".equals(subResourcePath)) {
                query.addScalar("sub_project_id", PostgresUUIDType.INSTANCE);
                query.addScalar("sub_project_name");
            }
            else {
                query.addScalar("construction_site_id", PostgresUUIDType.INSTANCE);
                query.addScalar("construction_site_name");
                if ("projects".equals(parentResourcePath)) {
                    query.addScalar("sub_project_id", PostgresUUIDType.INSTANCE);
                    query.addScalar("sub_project_name");
                }
            }
            @SuppressWarnings("unchecked")
            List<Object[]> rowObjects = query.list();

            // Prepare result: recursively retrieved resources mapped as lists (resources & their parents)
            // TODO: analyze object nesting [in JSON response] issues (cuz' potentially problematic in the future: exp. depth)
            List<Object> resources = new ArrayList<>();
            List<Map<String, Object>> resourcesParents = new ArrayList<>();
            for (Object[] rowObject : rowObjects) {
                resources.add(rowObject[0]);
                Map<String, Object> resourceParent = new HashMap<>();
                if ("constructionSites".equals(subResourcePath)) {
                    resourceParent.put("subProjectId", rowObject[1]);
                    resourceParent.put("subProjectName", rowObject[2]);
                }
                else {
                    resourceParent.put("constructionSiteId", rowObject[1]);
                    resourceParent.put("constructionSiteName", rowObject[2]);
                    if ("projects".equals(parentResourcePath)) {
                        resourceParent.put("subProjectId", rowObject[3]);
                        resourceParent.put("subProjectName", rowObject[4]);
                    }
                }
                resourcesParents.add(resourceParent);
            }
            Map<String, Object> recursivelyRetrievedResources = new HashMap<>();
            recursivelyRetrievedResources.put(subResourcePath, resources);
            recursivelyRetrievedResources.put("orderedProjectLevels", resourcesParents);

            return recursivelyRetrievedResources;
        }
        else {
            throw new IllegalArgumentException(URI_RESRC_INVALID_MSG);
        }
    }
}