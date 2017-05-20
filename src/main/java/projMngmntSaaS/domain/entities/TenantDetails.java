package projMngmntSaaS.domain.entities;

import javax.persistence.*;
import java.util.UUID;

/**
 * This contains a client's organization core details.
 */
@Entity
public class TenantDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 254)
    private String email;

    @Column(length = 2000)
    private String websiteUrl;


    public TenantDetails() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteURL) {
        this.websiteUrl = websiteURL;
    }
}
