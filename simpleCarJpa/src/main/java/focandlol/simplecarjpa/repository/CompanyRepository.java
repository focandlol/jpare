package focandlol.simplecarjpa.repository;

import focandlol.simplecarjpa.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
