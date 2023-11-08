package investments.api.core.bussinesRule;

import investments.api.adapters.dto.EnterpriseDTO;
import investments.api.core.domain.Enterprise;
import investments.api.infrastructure.repository.EnterpriseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EnterpriseBussines {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public Enterprise createEnterprise(Enterprise enterprise) {

        Enterprise existingEnterprise = enterpriseRepository.findByName(enterprise.getName());

        if (existingEnterprise != null) {
            throw new EntityNotFoundException("Empresa já cadastrada no sistema");

        }
        return enterpriseRepository.save(enterprise);


    }

}
