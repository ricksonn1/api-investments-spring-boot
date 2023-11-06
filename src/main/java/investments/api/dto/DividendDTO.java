package investments.api.dto;

import investments.api.domain.Enterprise;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DividendDTO(

        Long id,
        @NotNull
        LocalDate datePaidAmount,
        @NotNull
        BigDecimal amountPaid,
        Enterprise id_enterprise
) {
}
