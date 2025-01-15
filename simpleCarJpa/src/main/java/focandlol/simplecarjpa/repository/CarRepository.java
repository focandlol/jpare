package focandlol.simplecarjpa.repository;

import focandlol.simplecarjpa.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
