package hellojpare;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("sdsd");
            em.persist(team);
            Member member = new Member();
            member.setUsername("kkm");
            member.setAge(10);
            member.changeTeam(team);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("kkm2");
            //member2.setAge(20);
            member2.changeTeam(team);
            em.persist(member2);
            Member member3 = new Member();
            member3.setUsername("kkm3");
            //member3.setAge(30);
            em.persist(member3);

            em.flush();
            em.clear();

           String query = "select 'sdsd' from Member m join m.team t";
            List<String> singleResult = em.createQuery(query, String.class).getResultList();
            for (String s : singleResult) {
                System.out.println("s = " + s);
            }


            tx.commit();

        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}