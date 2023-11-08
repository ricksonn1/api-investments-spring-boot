package investments.api.adapters.controller;

import investments.api.core.domain.Enterprise;
import investments.api.adapters.dto.DataDetaisEnterprisesDTO;
import investments.api.adapters.dto.EnterpriseDTO;
import investments.api.infrastructure.repository.EnterpriseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("enterprises")
public class EnterpriseController {

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    //endpoint responsável por criar uma nova empresa.
    @PostMapping
    @Transactional
    public ResponseEntity createEnterprise(@RequestBody @Valid EnterpriseDTO data) {

        Enterprise newEnterprise = new Enterprise(data);
        enterpriseRepository.save(newEnterprise);
        return ResponseEntity.ok().build();

    }

    //endpoint responsável por buscar todas as empresas cadastradas.
    @GetMapping
    public ResponseEntity<Page<DataDetaisEnterprisesDTO>> getAllEnterprises(Pageable pageable) {
        var enterprise = enterpriseRepository.findAll(pageable).map(DataDetaisEnterprisesDTO::new);
        return ResponseEntity.ok(enterprise);
    }

    //endpoint responsável por buscar uma empresa pelo ID.
    @GetMapping("/{id}")
    public ResponseEntity getOneEnterprise(@PathVariable Long id) {

        var enterpriseById = enterpriseRepository.getReferenceById(id);
        return ResponseEntity.ok(new DataDetaisEnterprisesDTO(enterpriseById));
    }

    //endpoint responsável por atualizar os dados de uma empresa pelo ID.
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateEnterprise(@PathVariable Long id, @RequestBody @Valid EnterpriseDTO data) {
        Optional<Enterprise> optionalEnterprise = enterpriseRepository.findById(id);
        if (optionalEnterprise.isPresent()) {
            Enterprise enterprise = optionalEnterprise.get();
            enterprise.setName(data.name());
            enterprise.setTicker(data.ticker());
            enterprise.setSector(data.sector());
            return ResponseEntity.ok().build();
        } else {
            throw new EntityNotFoundException();
        }
    }

    //endpoint responsável por excluir uma empresa (e seus dividendos) pelo ID.
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletEnterprise(@PathVariable Long id) {

        enterpriseRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
