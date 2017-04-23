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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Document document = (Document) o;

        if (name != null ? !name.equals(document.name) : document.name != null) return false;
        return osPath != null ? osPath.equals(document.osPath) : document.osPath == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (osPath != null ? osPath.hashCode() : 0);
        return result;
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

    @Override
    public Document shallowClone() {
        Document clone = new Document();

        shallowCloneRootsInto(clone);
        clone.name = name;
        clone.osPath = osPath;

        return clone;
    }
}
