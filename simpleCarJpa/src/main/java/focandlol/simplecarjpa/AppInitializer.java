package focandlol.simplecarjpa;

import focandlol.simplecarjpa.domain.Car;
import focandlol.simplecarjpa.domain.Company;
import focandlol.simplecarjpa.service.CompanyService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AppInitializer {

    private final CompanyService companyService;

    @PostConstruct
    private void init(){

        List<Company> companyList = new ArrayList<>();
        companyList.add(new Company("기아","한국"));
        companyList.add(new Company("볼보","스웨덴"));
        companyList.add(new Company("르노","프랑스"));
        companyList.add(new Company("테슬라","미국"));
        companyList.add(new Company("뷰익","미국"));
        companyList.add(new Company("캐딜락","미국"));
        companyList.add(new Company("쉐보레","미국"));
        companyList.add(new Company("GMC","미국"));
        companyList.add(new Company("허머","미국"));
        companyList.add(new Company("폰티악","미국"));
        companyList.add(new Company("포드","미국"));
        companyList.add(new Company("링컨","미국"));
        companyList.add(new Company("크라이슬러","미국"));
        companyList.add(new Company("지프","미국"));

        companyService.saveAll(companyList);


    }
}
