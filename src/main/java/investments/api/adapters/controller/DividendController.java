package investments.api.adapters.controller;

import investments.api.core.bussinesRule.DividendBussines;
import investments.api.core.domain.Dividend;
import investments.api.core.domain.Enterprise;
import investments.api.adapters.dto.DataDetailsDividendDTO;
import investments.api.adapters.dto.DividendDTO;
import investments.api.infrastructure.exceptions.DividendAlreadyRegisteredException;
import investments.api.infrastructure.exceptions.EnterpriseNotFoundException;
import investments.api.infrastructure.repository.DividendRepository;
import investments.api.infrastructure.repository.EnterpriseRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping
public class DividendController {
    @Autowired
    private DividendRepository dividendRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private DividendBussines dividendBussines;

    //endpoint responsável por cadastrar um novo pagamento de dividendos para uma empresa específica.
    @PostMapping("enterprises/{id}/dividends")
    @Transactional
    public ResponseEntity createDividend(@PathVariable Long id, @RequestBody @Valid DividendDTO data) throws DividendAlreadyRegisteredException, EnterpriseNotFoundException {

        Dividend dividend = dividendBussines.createDividendForEnterprise(id, data);
        return ResponseEntity.ok(dividend);
    }

    //endpoint responsável por buscar todos os pagamentos de dividendos de uma empresa específica.
    @GetMapping("/enterprises/{id}/dividends")
    public ResponseEntity searchDividendsByCompany(@PathVariable Long id) {
        var dividends = enterpriseRepository.findById(id);
        return ResponseEntity.ok((dividends));
    }

    //endpoint responsável por buscar o pagamento de dividendos de uma empresa específica em uma determinada data.
    @GetMapping("/enterprises/{id}/dividends/{date}")
    public ResponseEntity<DataDetailsDividendDTO> getDividendByCompanyAndDate(@PathVariable Long id, @PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);

        Dividend dividend = dividendRepository.findByEnterpriseIdAndDateAmountPaid(id, localDate)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento de dividendos não encontrado para a empresa com o ID " + id + " na data " + date));
        return ResponseEntity.ok(new DataDetailsDividendDTO(dividend));
    }

    //endpoint responsável por atualizar o valor do pagamento de dividendos de uma empresa específica em uma determinada data.
    @PutMapping("/enterprises/{id}/dividends/{date}")
    @Transactional
    public ResponseEntity updateDividendByCompanyAndDate(@RequestBody @Valid DividendDTO data, @PathVariable Long id, @PathVariable String date) {

        LocalDate localDate = LocalDate.parse(date);

        Dividend dividend = dividendRepository.findByEnterpriseIdAndDateAmountPaid(id, localDate)
                .orElseThrow(() -> new EntityNotFoundException(("Pagamento de dividendos não encontrado para a empresa com o ID" + id + " na data " + date)));

        dividend.setAmountPaid(data.amountPaid());
        dividend = dividendRepository.save(dividend);


        Dividend dto = new Dividend();
        dto.setId(dividend.getId());
        dto.setDateAmountPaid(dividend.getDateAmountPaid());
        dto.setAmountPaid(dividend.getAmountPaid());
        dto.setEnterprise(dividend.getEnterprise());

        return ResponseEntity.ok(dto);
    }

    //endpoint responsável por excluir o pagamento de dividendos de uma empresa específica em uma determinada data.
    @DeleteMapping("/enterprise/{id}/dividends/{date}")
    @Transactional
    public ResponseEntity deletDividendByDate(@PathVariable Long id, @PathVariable String date) {

        LocalDate localDate = LocalDate.parse(date);
        Dividend dividend = dividendRepository.findByEnterpriseIdAndDateAmountPaid(id, localDate)
                .orElseThrow(() -> new EntityNotFoundException("Pagamento de dividendos não encontrado para a empresa com o ID" + id + "na data " + date));

        dividendRepository.delete(dividend);
        return ResponseEntity.ok().build();
    }
}

