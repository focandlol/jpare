package jpabookre.jpashop.domain;

import javax.persistence.*;

@Entity
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    private String city;

    private String street;

    private String zipcode;

    private DeliveryStatus deliveryStatus;
}
