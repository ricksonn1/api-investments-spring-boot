package investments.api.infrastructure.repository;

import investments.api.core.domain.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long> {
    @Query("SELECT e FROM Enterprise e WHERE e.name = :name")
    Enterprise findByName(@Param("name") String name);
}
