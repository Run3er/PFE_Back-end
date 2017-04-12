package entities;

/**
 * This contains a client's organization core details.
 */
public class Tenant {
    private String name;
    private String email;
    private String websiteUrl;

    public Tenant(String name, String email, String websiteUrl) {
        this.name = name;
        this.email = email;
        this.websiteUrl = websiteUrl;
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

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }
}
