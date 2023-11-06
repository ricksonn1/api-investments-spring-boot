package investments.api.controller;

import investments.api.domain.Dividend;
import investments.api.dto.DividendDTO;
import investments.api.repository.DividendRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dividends")
public class DividendController {

    @Autowired
    private DividendRepository dividendRepository;

    @PostMapping
    @Transactional
    public ResponseEntity createDividends(@RequestBody @Valid DividendDTO data) {

        Dividend newDividend = new Dividend(data);
        newDividend.setEnterprise(data.id_enterprise());
        dividendRepository.save(newDividend);
        return ResponseEntity.ok().build();

    }


}
