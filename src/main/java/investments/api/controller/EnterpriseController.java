package investments.api.controller;

import investments.api.domain.Enterprise;
import investments.api.dto.EnterpriseDTO;
import investments.api.repository.EnterpriseRepository;
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
    private EnterpriseRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity createEnterprise(@RequestBody @Valid EnterpriseDTO data) {

        Enterprise newEnterprise = new Enterprise(data);
        repository.save(newEnterprise);
        return ResponseEntity.ok().build();

    }

    @GetMapping
    public ResponseEntity<Page<Enterprise>> getAllEnterprises(Pageable pageable) {
        Page<Enterprise> enterprises = repository.findAll(pageable);
        return ResponseEntity.ok(enterprises);
    }

    @GetMapping("/{id}")
    public ResponseEntity getOneEnterprise(@PathVariable Long id) {

        var enterpriseById = repository.findById(id);
        return ResponseEntity.ok(enterpriseById);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateEnterprise(@PathVariable Long id, @RequestBody @Valid EnterpriseDTO data) {
        Optional<Enterprise> optionalEnterprise = repository.findById(id);
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

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletEnterprise(@PathVariable Long id) {

        var delet = repository.findById(id);
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
