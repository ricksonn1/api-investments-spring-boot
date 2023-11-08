package investments.api.adapters.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import investments.api.core.domain.Dividend;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DataDetailsDividendDTO(
        Long id,
        BigDecimal amountPaid,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dateAmountPaid,
        String enterpriseName) {
    public DataDetailsDividendDTO(Dividend dividend) {
        this(dividend.getId(), dividend.getAmountPaid(), dividend.getDateAmountPaid(), dividend.getEnterprise().getName());
    }
}

