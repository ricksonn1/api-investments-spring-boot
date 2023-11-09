package investments.api.core.domain;


import investments.api.adapters.dto.EnterpriseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Enterprise")
@Table(name = "enterprises")
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ticker;
    private String sector;

    @OneToMany(mappedBy = "enterprise", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Dividend> dividends = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    //
    public Enterprise(EnterpriseDTO data) {
        this.id = data.id();
        this.name = data.name();
        this.ticker = data.ticker();
        this.sector = data.sector();
        this.dividends = new ArrayList<>();
    }

    public void updateEnterprise(EnterpriseDTO data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.ticker() != null) {
            this.ticker = data.ticker();
        }
        if (data.sector() != null) {
            this.sector = data.sector();
        }
    }
}
