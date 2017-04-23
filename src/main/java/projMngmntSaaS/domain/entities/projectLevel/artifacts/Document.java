package projMngmntSaaS.domain.entities.projectLevel.artifacts;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * A document is basically a reference to a file on the system.
 * It is uniquely identified on the os file system and has a more descriptive name property set on submission.
 * TODO: handle file storage.
 */
@Entity
public class Document extends ProjectLevelArtifact<Document>
{
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 260)
    private String osPath;

    public Document() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOsPath(String osPath) {
        this.osPath = osPath;
    }
}
