package projMngmntSaaS.domain.entities;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

/**
 * Outside contributing organization entity.
 * Identifies uniquely a contributing outside user's organization, but can exist without any.
 */
@Entity
public class ContributorOrganization
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, length = 100)
    private String name;
    @OneToMany
    private List<Contribution> contributions;

    public ContributorOrganization() {
        // no-arg constructor for ORM (due to reflection use)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Contribution> getContributions() {
        return contributions;
    }

    public void setContributions(List<Contribution> contributions) {
        this.contributions = contributions;
    }
}