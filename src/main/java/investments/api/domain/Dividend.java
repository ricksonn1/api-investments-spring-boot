package investments.api.domain;

import investments.api.dto.DividendDTO;
import investments.api.repository.EnterpriseRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "dividend")
@Table(name = "dividends")
public class Dividend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amountPaid;
    private LocalDate datePaidAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_enterprise")
    private Enterprise enterprise;

    public Dividend(DividendDTO data) {
        this.amountPaid = data.amountPaid();
        this.datePaidAmount = data.datePaidAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDate getDatePaidAmount() {
        return datePaidAmount;
    }

    public void setDatePaidAmount(LocalDate datePaidAmount) {
        this.datePaidAmount = datePaidAmount;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }
}


