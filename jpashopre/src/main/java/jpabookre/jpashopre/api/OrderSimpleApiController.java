package jpabookre.jpashopre.api;

import jpabookre.jpashopre.domain.Address;
import jpabookre.jpashopre.domain.Order;
import jpabookre.jpashopre.domain.OrderStatus;
import jpabookre.jpashopre.domain.item.Book;
import jpabookre.jpashopre.domain.item.Item;
import jpabookre.jpashopre.repository.ItemRepository;
import jpabookre.jpashopre.repository.OrderRepository;
import jpabookre.jpashopre.repository.OrderSearch;
import jpabookre.jpashopre.repository.order.simplequery.OrderSimpleQueryDto;
import jpabookre.jpashopre.repository.order.simplequery.OrderSimpleQueryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;
    private final OrderSimpleQueryRepository orderSimpleQueryRepository;
    private final ItemRepository itemRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> ordersV1(){
        List<Order> all = orderRepository.findAll(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName();
            order.getDelivery().getAddress();
        }
        return all;
    }

    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAll(new OrderSearch());
        return orders.stream().map(o -> new SimpleOrderDto(o)).collect(Collectors.toList());
    }

    @GetMapping("/api/v3/simple-orders")
    public List<SimpleOrderDto> ordersV3() {
        List<Order> orders = orderRepository.findAllWithMemberDelivery();
        return orders.stream().map(o -> new SimpleOrderDto(o)).collect(Collectors.toList());
    }

    @GetMapping("/api/v4/simple-orders")
    public List<OrderSimpleQueryDto> ordersV4(){
        return orderSimpleQueryRepository.findOrderDtos();
    }


    @GetMapping("/a")
    public List<Item> aaa(){
        //List<Object> obj = new ArrayList<>();
        List<Item> all = itemRepository.findAll();

       /* for (Item item : all) {
            if(item instanceof Book){
                Book book = (Book) item;
                obj.add(book);
            }
        }
        for (Object o : obj) {
            System.out.println("o = " + o);
        }*/
        return all;
    }
    @GetMapping("/b")
    public List<Order> bbb() {
        List<Order> ad = orderRepository.ad();
        return ad;

    }



    @Data
    static class SimpleOrderDto{
        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName();
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress();
        }
    }

}
