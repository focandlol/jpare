package focandlol.simplecarjpa;

import focandlol.simplecarjpa.domain.Company;
import focandlol.simplecarjpa.service.CompanyService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AppInitializer {

    private final CompanyService companyService;

    @PostConstruct
    private void init(){
        Company company1 = Company.builder()
                .companyNation("korea")
                .companyName("hyundai")
                .build();

        Company company2 = Company.builder()
                .companyNation("germany")
                .companyName("bmw")
                .build();

        Company company3 = Company.builder()
                .companyNation("italy")
                .companyName("ferrari")
                .build();

       companyService.saveAll(List.of(company1, company2, company3));

    }
}
