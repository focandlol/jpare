package jpabookre.jpashopre.repository.order.simplequery;

import jpabookre.jpashopre.domain.Address;
import jpabookre.jpashopre.domain.Order;
import jpabookre.jpashopre.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {

    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Address address;

    public OrderSimpleQueryDto(Long id, String name, LocalDateTime orderDate, OrderStatus orderStatus,Address address) {
        this.orderId = id;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.address =address;
    }
}
