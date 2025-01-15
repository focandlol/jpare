package focandlol.simplecarjpa.repository;

import focandlol.simplecarjpa.domain.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "select c from Car c join fetch c.company",
            countQuery = "select count(c) from Car c")
    Page<Car> findAllFetch(Pageable pageable);
}
