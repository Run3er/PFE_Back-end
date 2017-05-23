package projMngmntSaaS.domain.entities;

import projMngmntSaaS.domain.entities.enums.ContributorOrganizationLegalType;
import projMngmntSaaS.domain.entities.enums.ContributorRole;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Outside contributing organization entity.
 * Identifies uniquely a contributing outside user's organization, but can exist without any.
 */
@Entity
public abstract class ContributorOrganization
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String fullName;

    private ContributorOrganizationLegalType legalType;

    @Column(length = 20)
    private String tradeRegister;

    private String address;

    @Column(nullable = false, length = 254)
    private String email;

    @Column(length = 150)
    private String telephoneNumbers;

    @Column(length = 150)
    private String faxNumbers;

    private String complementaryInfo;

    @Column(nullable = false, name = "role")
    private ContributorRole role;

    @OneToMany
    private List<Contribution> contributions;

    public ContributorOrganization() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ContributorOrganizationLegalType getLegalType() {
        return legalType;
    }

    public void setLegalType(ContributorOrganizationLegalType legalType) {
        this.legalType = legalType;
    }

    public String getTradeRegister() {
        return tradeRegister;
    }

    public void setTradeRegister(String tradeRegister) {
        this.tradeRegister = tradeRegister;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setContributions(List<Contribution> contributions) {
        this.contributions = contributions;
    }

    public List<Contribution> getContributions() {
        return contributions;
    }
}