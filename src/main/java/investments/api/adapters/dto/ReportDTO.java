package investments.api.adapters.dto;

import investments.api.core.domain.Enterprise;

import java.math.BigDecimal;

public record ReportDTO(
        String enterpriseName,
        BigDecimal totalDividendsPaid
) {
}
