package investments.api.controller;

import investments.api.domain.Dividend;
import investments.api.domain.Enterprise;
import investments.api.dto.DividendDTO;
import investments.api.dto.EnterpriseDTO;
import investments.api.repository.DividendRepository;
import investments.api.repository.EnterpriseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("dividends")
public class DividendController {
    @Autowired
    private DividendRepository dividendRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createDividend(@RequestBody @Valid DividendDTO data) {

        Enterprise enterprise = enterpriseRepository.findById(data.enterpriseId()).orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));

        Dividend dividend = new Dividend();
        dividend.setDateAmountPaid(data.dateAmountPaid());
        dividend.setAmountPaid(data.amountPaid());
        dividend.setEnterprise(enterprise);

        dividendRepository.save(dividend);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity getAllDividends() {
        return ResponseEntity.ok(dividendRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getDividendsById(@PathVariable Long id) {

        var dividendById = dividendRepository.findById(id);
        return ResponseEntity.ok(dividendById);
    }
}
