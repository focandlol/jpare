package focandlol.simplecarjpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.events.Event;

import java.util.Date;

@Entity
@Table(name = "company")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company extends BaseEntity{

    public Company(String companyName,String companyNation) {
        this.companyNation = companyNation;
        this.companyName = companyName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="company_name")
    private String companyName;

    @Column(name="company_nation")
    private String companyNation;

}
