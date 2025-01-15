package focandlol.simplecarjpa.controller;

import focandlol.simplecarjpa.domain.Car;
import focandlol.simplecarjpa.domain.Company;
import focandlol.simplecarjpa.dto.CarInputDto;
import focandlol.simplecarjpa.dto.CompanyInputDto;
import focandlol.simplecarjpa.repository.CarRepository;
import focandlol.simplecarjpa.repository.CompanyRepository;
import focandlol.simplecarjpa.service.CarService;
import focandlol.simplecarjpa.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BasicController {

    private final CompanyRepository companyRepository;
    private final CompanyService companyService;
    private final CarService carService;

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

    @GetMapping("/company")
    public String companyList(Pageable pageable, Model model){
        Page<Company> companyList = companyService.getCompanyByPage(pageable);
        model.addAttribute("companyList", companyList);

        return "companyList";
    }

    @GetMapping("/car")
    public String carForm(Model model){
        model.addAttribute("companyList",companyService.findAll());
        return "carForm";
    }

    @PostMapping("/car")
    public String saveCar(Model model, CarInputDto carInputDto){
        carService.saveCarInputDto(carInputDto);

        return "index";
    }

    @GetMapping("/carList")
    public String carList(Pageable pageable, Model model){
        Page<Car> carList = carService.getCarByPage(pageable);
        model.addAttribute("carList", carList);
        return "carList";
    }

}
