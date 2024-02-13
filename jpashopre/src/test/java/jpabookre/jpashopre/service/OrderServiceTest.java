package jpabookre.jpashopre.service;

import jakarta.persistence.EntityManager;
import jpabookre.jpashopre.domain.Address;
import jpabookre.jpashopre.domain.Member;
import jpabookre.jpashopre.domain.Order;
import jpabookre.jpashopre.domain.OrderStatus;
import jpabookre.jpashopre.domain.item.Book;
import jpabookre.jpashopre.exception.NotEnoughStockException;
import jpabookre.jpashopre.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void order(){
        Member member = createMember();

        Book book = createBook("ads", 1000, 10);

        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.ORDER,getOrder.getStatus(),"상품 주문시 상태는 order");
        assertEquals(1,getOrder.getOrderItems().size(),"주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(1000*orderCount, getOrder.getTotalPrice(),"주문 가격은 가격 * 수량이다");
        assertEquals(book.getStockQuantity(),8,"주문 수량만큼 재고가 줄어야 한다");


    }

    @Test
    public void cancel(){
        Member member = createMember();
        Book book = createBook("asd", 1000, 10);
        int orderCount = 2;

        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);

        assertEquals(OrderStatus.CANCEL,getOrder.getStatus(),"주문 취소시 상태는 cancel");
        assertEquals(10,book.getStockQuantity(),"주문 취소된 상품은 그만큰 재고가 증가해야 한다.");
    }

    @Test
    public void 상품주문_재고수량초과(){
        Member member = createMember();
        Book book = createBook("ads", 1000, 10);

        int orderPrice = 11;

        assertThrows(NotEnoughStockException.class,
                () -> orderService.order(member.getId(),book.getId(),orderPrice));
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123123"));
        em.persist(member);
        return member;
    }
}