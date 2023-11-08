package investments.api.adapters.dto;

import investments.api.core.domain.Enterprise;

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
