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
import java.util.ArrayList;
import java.util.List;

@Controller
public class DividendBussines {

    @Autowired
    private DividendRepository dividendRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;


    public Dividend createDividendForEnterprise(DividendDTO data, Long id) throws EnterpriseNotFoundException, DividendAlreadyRegisteredException{

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

    public List<DataDetailsDividendDTO> getDividendByCompanyAndDate(Long id, String date) {

        LocalDate localDate = LocalDate.parse(date);
        List<Dividend> dividendos = dividendRepository.findByEnterpriseIdAndDate(id, localDate);
        List<DataDetailsDividendDTO> resultado = new ArrayList<>();
        for (Dividend dividendo : dividendos) {
            resultado.add(new DataDetailsDividendDTO(dividendo.getId(), dividendo.getAmountPaid(), dividendo.getDateAmountPaid(), dividendo.getEnterprise().getName()));
        }
        return resultado;
    }

    public List<DataDetailsDividendDTO> updateDividendsAndCompanyForDate(Long id, DividendDTO data, String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<Dividend> dividendList = dividendRepository.findByEnterpriseIdAndDate(id, localDate);
        List<DataDetailsDividendDTO> resultado = new ArrayList<>();
        for (Dividend dividend : dividendList) {
            dividend.setAmountPaid(data.amountPaid());
            dividend = dividendRepository.save(dividend);
            resultado.add(new DataDetailsDividendDTO(dividend.getId(), dividend.getAmountPaid(), dividend.getDateAmountPaid(), dividend.getEnterprise().getName()));
        }
        return resultado;
    }

    public void deleteDividendsFromSpecificCompanyOnDate(Long id, String date) {
        LocalDate localDate = LocalDate.parse(date);

        dividendRepository.deleteByEnterpriseIdAndDate(id, localDate);
    }
}