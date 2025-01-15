package focandlol.simplecarjpa.controller;

import focandlol.simplecarjpa.dto.CompanyInputDto;
import focandlol.simplecarjpa.repository.CompanyRepository;
import focandlol.simplecarjpa.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BasicController {

    private final CompanyRepository companyRepository;
    private final CompanyService companyService;

    @GetMapping("/")
    public String index(Model model){
        return "index";
    }

    @GetMapping("/save")
    public String companyForm(Model model){
        return "companyForm";
    }

    @PostMapping("/save")
    public String saveCompany(Model model, CompanyInputDto companyInputDto){
        companyService.saveCompanyInputDto(companyInputDto);

        return "index";
    }

}
