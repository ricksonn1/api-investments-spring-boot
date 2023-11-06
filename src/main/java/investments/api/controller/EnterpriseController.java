package investments.api.controller;

import investments.api.domain.Enterprise;
import investments.api.dto.EnterpriseDTO;
import investments.api.repository.EnterpriseRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
