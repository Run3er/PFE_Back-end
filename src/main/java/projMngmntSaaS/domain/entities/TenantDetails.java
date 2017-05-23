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
    private String fullName;

    @Column(nullable = false, length = 254)
    private String email;

    @Column(length = 2000)
    private String websiteUrl;

    @Column(length = 150)
    private String telephoneNumbers;

    @Column(length = 150)
    private String faxNumbers;

    private String complementaryInfo;

    private String address;

    @Column(length = 254)
    private String contactEmail;

    @Column(length = 100)
    private String contactName;

    @Column(length = 100)
    private String contactTelephoneNumbers;

    @Column(unique = true, nullable = false)
    private String clientCode = UUID.randomUUID().toString().split("-", 2)[0];


    public TenantDetails() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getTelephoneNumbers() {
        return telephoneNumbers;
    }

    public void setTelephoneNumbers(String telephoneNumbers) {
        this.telephoneNumbers = telephoneNumbers;
    }

    public String getFaxNumbers() {
        return faxNumbers;
    }

    public void setFaxNumbers(String faxNumbers) {
        this.faxNumbers = faxNumbers;
    }

    public String getComplementaryInfo() {
        return complementaryInfo;
    }

    public void setComplementaryInfo(String complementaryInfo) {
        this.complementaryInfo = complementaryInfo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTelephoneNumbers() {
        return contactTelephoneNumbers;
    }

    public void setContactTelephoneNumbers(String contactTelephoneNumbers) {
        this.contactTelephoneNumbers = contactTelephoneNumbers;
    }

    public String getClientCode() {
        return clientCode;
    }
}
