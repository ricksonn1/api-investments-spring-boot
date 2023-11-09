package investments.api.core.bussinesRule;

import investments.api.adapters.dto.DataDetailsDividendDTO;
import investments.api.adapters.dto.DividendDTO;
import investments.api.core.domain.Dividend;
import investments.api.core.domain.Enterprise;
import investments.api.infrastructure.exceptions.DividendAlreadyRegisteredException;
import investments.api.infrastructure.exceptions.EnterpriseNotFoundException;
import investments.api.infrastructure.repository.DividendRepository;
import investments.api.infrastructure.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class DividendBussines {

    @Autowired
    private DividendRepository dividendRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private EnterpriseBussines enterpriseBussines;

    public Dividend createDividendForEnterprise(Long id, DividendDTO data) throws DividendAlreadyRegisteredException {

        Enterprise enterprise = enterpriseRepository.findById(data.enterpriseId())
                .orElseThrow(() -> new EnterpriseNotFoundException(id));

        Dividend dividend = new Dividend();
        dividend.setDateAmountPaid(data.dateAmountPaid());
        dividend.setAmountPaid(data.amountPaid());
        dividend.setEnterprise(enterprise);

        return dividendRepository.save(dividend);
    }

    public Enterprise getDividendsByCompany(Long id) {
        var dividendsAndEnterprise = enterpriseRepository.findById(id);
        return dividendsAndEnterprise.get();
    }

//    public DataDetailsDividendDTO getDividendByCompanyAndDate(Long id, String date) {
//        LocalDate localDate = LocalDate.parse(date);
//
//        Optional<Dividend> dividend = dividendRepository.findByEnterpriseIdAndDateAmountPaid(id, localDate);
//        return new DataDetailsDividendDTO(dividend);
//    }

}
