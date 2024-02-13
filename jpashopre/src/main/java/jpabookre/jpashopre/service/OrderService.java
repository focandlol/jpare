package jpabookre.jpashopre.service;

import jpabookre.jpashopre.domain.Delivery;
import jpabookre.jpashopre.domain.Member;
import jpabookre.jpashopre.domain.Order;
import jpabookre.jpashopre.domain.OrderItem;
import jpabookre.jpashopre.domain.item.Item;
import jpabookre.jpashopre.repository.ItemRepository;
import jpabookre.jpashopre.repository.MemberRepository;
import jpabookre.jpashopre.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId,int count){

        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        //int i=3;
        //OrderItem[] a = new OrderItem[i];
       // a[0] = orderItem;

        Order order = Order.createOrder(member, delivery,orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId){
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }


}
