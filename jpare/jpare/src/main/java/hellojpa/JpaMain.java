package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = em.find(Member.class, 160L);
            member.setName("1234");

            Member findMember = em.find(Member.class, 160L);
            System.out.println("findMember = " + findMember.getName());
            System.out.println("member = " + member);
            System.out.println("findMember = " + findMember);
            tx.commit();
            System.out.println(" ======================== ");
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
