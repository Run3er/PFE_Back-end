package projMngmntSaaS.multiTenancy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Contains a tenant information, administration-wise.
 */
@Entity
public class Tenant
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

//    @Column(unique = true, nullable = false, length = 100)
    private String pseudo;

    public Tenant() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public UUID getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    // Tenant user name & schema name supposed to be the same as its id
    @JsonIgnore
    public String getDbSchemaName() {
        return id + "";
    }
}
