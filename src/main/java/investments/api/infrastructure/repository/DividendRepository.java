package investments.api.infrastructure.repository;

import investments.api.core.domain.Dividend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DividendRepository extends JpaRepository<Dividend, Long> {

    @Query("SELECT d FROM Dividend d JOIN FETCH d.enterprise e WHERE e.id = ?1")
    Dividend findByEnterpriseId(Long enterpriseId);

    @Query("SELECT d FROM Dividend d WHERE d.enterprise.id = :enterpriseId AND d.dateAmountPaid = :dateAmountPaid")
    Optional<Dividend> findByEnterpriseIdAndDateAmountPaid(@Param("enterpriseId") Long enterpriseId, @Param("dateAmountPaid") LocalDate dateAmountPaid);
}

