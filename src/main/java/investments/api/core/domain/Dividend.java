package investments.api.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "Dividend")
@Table(name = "dividends")
public class Dividend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate dateAmountPaid;

    @Column(nullable = false)
    private BigDecimal amountPaid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enterprise_id")
    @JsonIgnore
    private Enterprise enterprise;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateAmountPaid() {
        return dateAmountPaid;
    }

    public void setDateAmountPaid(LocalDate dateAmountPaid) {
        this.dateAmountPaid = dateAmountPaid;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }
}
