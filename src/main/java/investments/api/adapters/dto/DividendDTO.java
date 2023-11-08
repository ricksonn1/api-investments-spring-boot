package investments.api.adapters.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DividendDTO(
        Long id,
        BigDecimal amountPaid,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dateAmountPaid,
        Long enterpriseId) {

}
