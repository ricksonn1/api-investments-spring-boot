package investments.api.adapters.controller;

import investments.api.adapters.dto.DataDetailsDividendDTO;
import investments.api.adapters.dto.ReportDTO;
import investments.api.core.bussinesRule.EnterpriseBussines;
import investments.api.core.bussinesRule.ReportBussines;
import investments.api.infrastructure.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping
public class ReportController {

    @Autowired
    private ReportBussines reportBussines;

    @GetMapping("/enterprise/{id}/dividends/{date}")
    public ResponseEntity getTotalAmountPaidBySpecificCompanyReport(@PathVariable Long id, @PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);

        BigDecimal totalAmountPaid = reportBussines.getTotalAmountPaidBySpecificCompany(id, String.valueOf(localDate));
        String enterpriseName = reportBussines.getEnterpriseNameById(id);
        ReportDTO reportDTO = new ReportDTO(enterpriseName, totalAmountPaid);
        return ResponseEntity.ok(reportDTO);
    }

    @GetMapping("/enterprise/{id}/dividends/todas-as-datas")
    public ResponseEntity<List<DataDetailsDividendDTO>> getDividendsAllDates(@PathVariable Long id) {

        List<DataDetailsDividendDTO> dividends = reportBussines.getDividendsAllOfDate(id);
        return ResponseEntity.ok(dividends);
    }
}
