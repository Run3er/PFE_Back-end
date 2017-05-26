package projMngmntSaaS.repositories.general;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

/**
 * Common repository interface for all entities repositories to extend.
 * Provides common CRUD methods across them all.
 */
@NoRepositoryBean
public interface EntitiesRepository<T> extends JpaRepository<T, UUID>
{
}
