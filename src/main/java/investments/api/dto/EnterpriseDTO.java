package investments.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnterpriseDTO(

        Long id,
        @NotBlank
        String name,
        @NotBlank
        String ticker,
        @NotBlank
        String sector

) {
}
