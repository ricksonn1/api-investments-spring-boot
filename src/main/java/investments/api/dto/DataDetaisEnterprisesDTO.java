package investments.api.dto;

import investments.api.domain.Enterprise;
import jakarta.validation.constraints.NotBlank;

public record DataDetaisEnterprisesDTO(
        Long id,
        String name,
        String ticker,
        String sector
) {

    public DataDetaisEnterprisesDTO(Enterprise enterprise) {
        this(enterprise.getId(), enterprise.getName(), enterprise.getTicker(), enterprise.getSector());
    }
}
