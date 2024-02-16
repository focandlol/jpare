package jpabookre.jpashop;

import jpabookre.jpashop.domain.Member;
import jpabookre.jpashop.domain.Order;
import jpabookre.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setName("kkm");

            Order order1 = new Order();
            order1.setOrderDate(LocalDateTime.now());
            order1.setCreatedBy("order1");
            order1.setMember(member);

            Order order2 = new Order();
            order2.setOrderDate(LocalDateTime.now());
            order2.setCreatedBy("order2");

            OrderItem orderItem1 = new OrderItem();
            orderItem1.setOrderPrice(1000);
            orderItem1.setCount(1);

            OrderItem orderItem2 = new OrderItem();
            orderItem2.setOrderPrice(2000);
            orderItem2.setCount(2);

            OrderItem orderItem3 = new OrderItem();
            orderItem3.setOrderPrice(3000);
            orderItem3.setCount(3);

            OrderItem orderItem4 = new OrderItem();
            orderItem4.setOrderPrice(4000);
            orderItem4.setCount(4);

            order1.addOrderItem(orderItem1);
            order1.addOrderItem(orderItem2);

            order2.addOrderItem(orderItem3);
            order2.addOrderItem(orderItem4);

            em.persist(order1);
            em.persist(order2);
            em.persist(member);

            em.flush();
            em.clear();

            //List<Order> resultList = em.createQuery("select distinct o from Order o join fetch o.member m join o.orderItems oi", Order.class) //가능
            List<Order> resultList = em.createQuery("select distinct o from Order o join o.orderItems oi", Order.class)
                    .setFirstResult(0)
                    .setMaxResults(1).getResultList();
            System.out.println("resultList = " + resultList.size());
            for (Order order : resultList) {
                System.out.println("order = " + order);
            }

            /*List<Order> resultList = em.createQuery("select distinct oi.order from OrderItem oi where oi.count =1 or oi.count=2", Order.class)
                    .setFirstResult(0)
                    .setMaxResults(3)
                    .getResultList();
            System.out.println("resultList = " + resultList.size());*/

            tx.commit();

        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

}
