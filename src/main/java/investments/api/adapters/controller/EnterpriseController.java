package investments.api.adapters.controller;

import investments.api.core.bussinesRule.EnterpriseBussines;
import investments.api.core.domain.Enterprise;
import investments.api.adapters.dto.DataDetaisEnterprisesDTO;
import investments.api.adapters.dto.EnterpriseDTO;
import investments.api.infrastructure.exceptions.EnterpriseAlreadyRegisteredException;
import investments.api.infrastructure.exceptions.EnterpriseNotFoundException;
import investments.api.infrastructure.repository.EnterpriseRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("enterprises")
@SecurityRequirement(name = "bearer-key")
public class EnterpriseController {

    @Autowired
    private EnterpriseBussines enterpriseBussines;

    //endpoint responsável por criar uma nova empresa.
    @PostMapping
    @Transactional
    public ResponseEntity createEnterprise(@RequestBody @Valid EnterpriseDTO data) throws EnterpriseAlreadyRegisteredException {

        Enterprise newEnterprise = new Enterprise(data);
        var enterprise = enterpriseBussines.createEnterprise(newEnterprise);
        return ResponseEntity.status(HttpStatus.CREATED).body(enterprise);

    }

    //endpoint responsável por buscar todas as empresas cadastradas.
    @GetMapping
    public ResponseEntity<Page<DataDetaisEnterprisesDTO>> getAllEnterprises(Pageable pageable) {
        var enterprise = enterpriseBussines.getAllEnterprises(pageable);
        return ResponseEntity.ok(enterprise);
    }

    //endpoint responsável por buscar uma empresa pelo ID.
    @GetMapping("/{id}")
    public ResponseEntity getOneEnterprise(@PathVariable Long id) throws EnterpriseNotFoundException {

        var enterpriseById = enterpriseBussines.getOneEnterprise(id);
        return ResponseEntity.ok(new DataDetaisEnterprisesDTO(enterpriseById));
    }

    //endpoint responsável por atualizar os dados de uma empresa pelo ID.
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateEnterprise(@PathVariable Long id, @RequestBody @Valid EnterpriseDTO data) throws EnterpriseNotFoundException {
        enterpriseBussines.updateEnterprise(id, data);
        return ResponseEntity.noContent().build();
    }

    //endpoint responsável por excluir uma empresa (e seus dividendos) pelo ID.
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletEnterprise(@PathVariable Long id) throws EnterpriseNotFoundException {

        enterpriseBussines.deletEnterprise(id);
        return ResponseEntity.noContent().build();
    }
}
