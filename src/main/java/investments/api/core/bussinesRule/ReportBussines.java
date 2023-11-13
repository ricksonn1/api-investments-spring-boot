package investments.api.core.bussinesRule;

import investments.api.adapters.dto.DataDetailsDividendDTO;
import investments.api.adapters.dto.DividendDTO;
import investments.api.core.domain.Dividend;
import investments.api.core.domain.Enterprise;
import investments.api.infrastructure.exceptions.DividendNotFoundException;
import investments.api.infrastructure.exceptions.EnterpriseNotFoundException;
import investments.api.infrastructure.repository.DividendRepository;
import investments.api.infrastructure.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
public class ReportBussines {

    @Autowired
    private DividendRepository dividendRepository;
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    public BigDecimal getTotalAmountPaidBySpecificCompany(Long id, String date) {

        BigDecimal totalAmountPaid = BigDecimal.ZERO;
        LocalDate localDate = LocalDate.parse(date);
        List<Dividend> dividendos = dividendRepository.findByEnterpriseIdAndDate(id, localDate);
        if (dividendos.isEmpty()) {
            throw new DividendNotFoundException(date);
        }
        for (Dividend dividendo : dividendos) {
            totalAmountPaid = totalAmountPaid.add(dividendo.getAmountPaid());
        }
        return totalAmountPaid;

    }

    public List<DataDetailsDividendDTO> getDividendsAllOfDate(Long id) {
        List<Dividend> dividendos = dividendRepository.findByEnterpriseId(id);
        List<DataDetailsDividendDTO> dividendosDTO = new ArrayList<>();
        for (Dividend dividendo : dividendos) {
            DataDetailsDividendDTO dividendoDTO = new DataDetailsDividendDTO(
                    dividendo.getEnterprise().getId(),
                    dividendo.getAmountPaid(),
                    dividendo.getDateAmountPaid(),
                    dividendo.getEnterprise().getName()
            );
            dividendosDTO.add(dividendoDTO);
        }
        return dividendosDTO;
    }


    public String getEnterpriseNameById(Long id) {
        Optional<Enterprise> enterpriseOptional = enterpriseRepository.findById(id);
        if (enterpriseOptional.isPresent()) {
            return enterpriseOptional.get().getName();
        } else {
            throw new EnterpriseNotFoundException(id);
        }
    }
}
