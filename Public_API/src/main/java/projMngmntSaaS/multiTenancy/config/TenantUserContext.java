package projMngmntSaaS.multiTenancy.config;

import java.util.UUID;

/**
 * Service for setting the current tenant user to check against for user related operations.
 */
public class TenantUserContext
{
    private static ThreadLocal<UUID> currentUser = ThreadLocal.withInitial(() -> null);

    public static void setCurrentUser(UUID user) {
        currentUser.set(user);
    }

    public static UUID getCurrentUser() {
        return currentUser.get();
    }

    public static void clear() {
        currentUser.remove();
    }
}