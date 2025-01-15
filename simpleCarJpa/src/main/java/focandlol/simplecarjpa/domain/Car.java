package focandlol.simplecarjpa.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "car")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model_name")
    private String modelName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

    @Column(name = "passenger_capacity")
    private Integer passengerCapacity;

}
