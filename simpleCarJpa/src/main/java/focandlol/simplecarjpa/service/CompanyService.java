package focandlol.simplecarjpa.service;

import focandlol.simplecarjpa.domain.Company;
import focandlol.simplecarjpa.dto.CompanyInputDto;
import focandlol.simplecarjpa.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company saveCompanyInputDto(CompanyInputDto companyInputDto) {
        return companyRepository.save(Company.builder()
                .companyNation(companyInputDto.getCompanyNation())
                .companyName(companyInputDto.getCompanyName())
                .build());
    }

    public List<Company> saveAll(List<Company> companies) {
        return companyRepository.saveAll(companies);
    }
}
