package projMngmntSaaS.domain.utils;

/**
 * Provides method for shallow cloning an entity (exp. no id, ...).
 */
public interface CloneableEntity<T>
{
    T shallowClone();
}
