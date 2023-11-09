package investments.api.core.bussinesRule;

import investments.api.adapters.dto.DataDetaisEnterprisesDTO;
import investments.api.adapters.dto.EnterpriseDTO;
import investments.api.core.domain.Enterprise;
import investments.api.infrastructure.exceptions.EnterpriseAlreadyRegisteredException;
import investments.api.infrastructure.exceptions.EnterpriseNotFoundException;
import investments.api.infrastructure.repository.EnterpriseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class EnterpriseBussines {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public Enterprise createEnterprise(Enterprise enterprise) throws EnterpriseAlreadyRegisteredException {

        if (enterpriseRepository.findByName(enterprise.getName()).isPresent()) {
            throw new EntityNotFoundException(enterprise.getName());
        }
        return enterpriseRepository.save(enterprise);
    }

    public Page<DataDetaisEnterprisesDTO> getAllEnterprises(Pageable pageable) {
        return enterpriseRepository.findAll(pageable).map(DataDetaisEnterprisesDTO::new);

    }

    public Enterprise getOneEnterprise(Long id) throws EnterpriseNotFoundException {
        Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
        if (!enterprise.isPresent()) {
            throw new EnterpriseNotFoundException(id);
        }
        return enterprise.get();
    }

    public void updateEnterprise(Long id, EnterpriseDTO data) throws EnterpriseNotFoundException {
        Optional<Enterprise> optionalEnterprise = enterpriseRepository.findById(id);
        if (optionalEnterprise.isPresent()) {
            Enterprise enterprise = optionalEnterprise.get();
            enterprise.setName(data.name());
            enterprise.setTicker(data.ticker());
            enterprise.setSector(data.sector());
            enterprise.updateEnterprise(data);
        } else {
            throw new EnterpriseNotFoundException(id);
        }
    }

    public void deletEnterprise(Long id) {
        Optional<Enterprise> enterprise = enterpriseRepository.findById(id);
        if (enterprise.isPresent()) {
            enterpriseRepository.deleteById(id);
        } else {
            throw new EnterpriseNotFoundException(id);
        }
    }
}

