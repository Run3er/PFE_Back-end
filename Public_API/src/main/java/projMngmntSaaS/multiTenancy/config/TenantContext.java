package projMngmntSaaS.multiTenancy.config;

/**
 * Service for setting the current tenant to be associated with upcoming sessions/entityManagers instantiation.
 */
public class TenantContext
{
    public final static String TENANTS_GLOBAL = "tenants_global";
    public final static String DEFAULT_TENANT = "43d44eae-cd10-4ffb-97e1-c3e189119659";

    private static ThreadLocal<String> currentTenant = ThreadLocal.withInitial(() -> DEFAULT_TENANT);

    public static void setCurrentTenant(String tenant) {
        currentTenant.set(tenant);
    }

    public static String getCurrentTenant() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}