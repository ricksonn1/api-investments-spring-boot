package investments.api.adapters.controller;

import investments.api.adapters.dto.DataDetailsDividendDTO;
import investments.api.core.bussinesRule.DividendBussines;
import investments.api.core.domain.Dividend;
import investments.api.adapters.dto.DividendDTO;
import investments.api.infrastructure.exceptions.DividendAlreadyRegisteredException;
import investments.api.infrastructure.exceptions.EnterpriseNotFoundException;
import investments.api.infrastructure.repository.DividendRepository;
import investments.api.infrastructure.repository.EnterpriseRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping
public class DividendController {
    @Autowired
    private DividendRepository dividendRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private DividendBussines dividendBussines;

    @PostMapping("enterprises/{id}/dividends")
    @Transactional
    public ResponseEntity createDividend(@PathVariable Long id, @RequestBody @Valid DividendDTO data) throws DividendAlreadyRegisteredException, EnterpriseNotFoundException {

        Dividend dividend = dividendBussines.createDividendForEnterprise(data, id);
        return ResponseEntity.ok(dividend);
    }

    @GetMapping("/enterprises/{id}/dividends")
    public ResponseEntity searchDividendsByCompany(@PathVariable Long id) {
        var dividends = dividendBussines.getDividendsByCompany(id);
        return ResponseEntity.ok((dividends));
    }

    @GetMapping("/enterprises/{id}/dividends/{date}")
    public ResponseEntity<List<DataDetailsDividendDTO>> getDividendByCompanyAndDate(@PathVariable Long id, @PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);

        List<DataDetailsDividendDTO> dividend = dividendBussines.getDividendByCompanyAndDate(id, String.valueOf(localDate));

        return ResponseEntity.ok(dividend);
    }

    @PutMapping("/enterprises/{id}/dividends/{date}")
    @Transactional
    public ResponseEntity<List<DataDetailsDividendDTO>> updateDividendByCompanyAndDate(@RequestBody @Valid DividendDTO data, @PathVariable Long id, @PathVariable String date) {

        LocalDate localDate = LocalDate.parse(date);
        List<DataDetailsDividendDTO> updatedDividend = dividendBussines.updateDividendsAndCompanyForDate(id, data, String.valueOf(localDate));
        return ResponseEntity.ok(updatedDividend);
    }


    @DeleteMapping("/enterprise/{id}/dividends/{date}")
    @Transactional
    public ResponseEntity deletDividendByDate(@PathVariable Long id, @PathVariable String date) {

        LocalDate localDate = LocalDate.parse(date);
        dividendBussines.deleteDividendsFromSpecificCompanyOnDate(id, String.valueOf(localDate));
        return ResponseEntity.noContent().build();
    }
}

