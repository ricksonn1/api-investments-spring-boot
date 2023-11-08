package investments.api.adapters.dto;

import investments.api.core.domain.Enterprise;
import jakarta.validation.constraints.NotBlank;

public record EnterpriseDTO(

        Long id,
        @NotBlank
        String name,
        @NotBlank
        String ticker,
        @NotBlank
        String sector

) {

        public EnterpriseDTO(Enterprise enterprise) {
            this(enterprise.getId(), enterprise.getName(), enterprise.getTicker(), enterprise.getSector());
        }
}