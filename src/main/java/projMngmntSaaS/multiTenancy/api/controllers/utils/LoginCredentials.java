package projMngmntSaaS.multiTenancy.api.controllers.utils;

/**
 * {@link org.springframework.web.bind.annotation.RequestBody} login credentials.
 */
public class LoginCredentials
{
    private String user;
    private String password;
    private Boolean persistent;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPersistent() {
        return persistent;
    }

    public void setPersistent(Boolean persistent) {
        this.persistent = persistent;
    }
}
