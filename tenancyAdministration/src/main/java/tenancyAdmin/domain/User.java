package tenancyAdmin.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import tenancyAdmin.domain.utils.PasswordHashing;

import javax.persistence.*;
import java.util.UUID;

/**
 * User entity.
 */
@Entity
// Using "user_account" because "user" is a reserved keyword in postgresql
@Table(name = "user_account")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true, nullable = false, length = 10)
    private String login;

    @JsonProperty(value = "password", access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private byte[] passwordHash;

    @JsonIgnore
    @Column(nullable = false)
    private byte[] passwordSalt;

    @Column(nullable = false, length = 40)
    private String firstName;

    @Column(nullable = false, length = 40)
    private String lastName;


    public User() {
        // no-arg constructor for ORM (because of reflection use)
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public byte[] getPasswordSalt() {
        return this.passwordSalt;
    }

    public byte[] getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String password) {
        this.passwordSalt = PasswordHashing.getNextSalt();
        this.passwordHash = PasswordHashing.hash(password, this.passwordSalt);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
