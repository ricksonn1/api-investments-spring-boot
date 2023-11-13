package investments.api.infrastructure.repository;

import investments.api.adapters.dto.DataDetailsDividendDTO;
import investments.api.adapters.dto.DataDetaisEnterprisesDTO;
import investments.api.adapters.dto.DividendDTO;
import investments.api.core.domain.Dividend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DividendRepository extends JpaRepository<Dividend, Long> {

    @Query("SELECT d FROM Dividend d WHERE d.enterprise.id = :id AND d.dateAmountPaid = :date")
    List<Dividend> findByEnterpriseIdAndDate(@Param("id") Long id, @Param("date") LocalDate date);

    @Modifying
    @Query("DELETE FROM Dividend d WHERE d.enterprise.id = :id AND d.dateAmountPaid = :date")
    void deleteByEnterpriseIdAndDate(Long id, LocalDate date);

    @Query("SELECT d FROM Dividend d WHERE d.id = :id AND d.enterprise.name = :name")
    Optional<Dividend> findByIdAndEnterpriseName(@Param("id") Long id, @Param("name") String name);


    @Query("SELECT d FROM Dividend d WHERE d.enterprise.id = :enterpriseId")
    List<Dividend> findByEnterpriseId(@Param("enterpriseId") Long enterpriseId);
}

