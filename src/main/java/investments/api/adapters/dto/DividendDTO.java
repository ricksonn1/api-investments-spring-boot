package investments.api.adapters.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import investments.api.core.domain.Dividend;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DividendDTO(
        Long id,
        BigDecimal amountPaid,
        @JsonFormat(pattern = "dd/MM/yyyy")
        @DateTimeFormat(pattern = "dd/MM/yyyy")
        LocalDate dateAmountPaid,
        Long enterpriseId
) {
}
