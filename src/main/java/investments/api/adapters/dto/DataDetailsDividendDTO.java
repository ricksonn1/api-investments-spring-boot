package investments.api.adapters.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import investments.api.core.domain.Dividend;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public record DataDetailsDividendDTO(
        Long id,
        BigDecimal amountPaid,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate dateAmountPaid,
        String enterpriseName) {
    public DataDetailsDividendDTO(Dividend dividend) {
        this(dividend.getId(), dividend.getAmountPaid(), dividend.getDateAmountPaid(), String.valueOf(dividend.getEnterprise().getName()));
    }

}

