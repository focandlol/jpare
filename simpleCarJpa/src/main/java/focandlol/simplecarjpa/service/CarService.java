package focandlol.simplecarjpa.service;

import focandlol.simplecarjpa.domain.Car;
import focandlol.simplecarjpa.domain.Company;
import focandlol.simplecarjpa.dto.CarInputDto;
import focandlol.simplecarjpa.repository.CarRepository;
import focandlol.simplecarjpa.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService {

    private final CarRepository carRepository;
    private final CompanyRepository companyRepository;

    public Car saveCarInputDto(CarInputDto carInputDto) {
        return carRepository.save(Car.builder()
                        .modelName(carInputDto.getModelName())
                        .passengerCapacity(carInputDto.getPassengerCapacity())
                        .company(companyRepository.findById(carInputDto.getCompanyId()).get())
                .build());
    }

    public Page<Car> getCarByPage(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        return carRepository.findAll(PageRequest.of(page, 5, pageable.getSort()));
    }
}
