package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Movie movie = new Movie();
 *             movie.setPrice(10000);
 *             movie.setName("sdad");
 *             movie.setDirector("ssss");
 *             movie.setActor("asad");
 *
 *             em.persist(movie);
 *
 *             em.flush();
 *             em.clear();
 *
 *             Item item = em.find(Item.class, movie.getId());
 *
 *
 *             if(item instanceof Book){
 *                 System.out.println("영화오낭혼밍ㄻ");
 *             }
 *             if(item instanceof Movie){
 *                 System.out.println("item은 영화입니다 = ");
 *                 Movie item1 = (Movie) item;
 *                 item1.getActor();
 *             }
 */
public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("kkm");
            em.persist(member);

            em.flush();
            em.clear();

            Member result = em.createQuery("select m from Member m where m.username = 'kkm'", Member.class).getSingleResult();
            System.out.println("result = " + result);
            System.out.println("result = " + result.getAddressHistory());

            tx.commit();

        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
