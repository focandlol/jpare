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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Team team1 = em.find(Team.class, team.getId());
            List<Member> members = team1.getMembers();
           Member member1 = members.get(0);
            System.out.println("member1sd = " + member1.getTeam().getClass());
            System.out.println("member1 = " + member1.getLocker().getClass());

            //Member member1 = em.find(Member.class, member.getId());
            //System.out.println("member1 = " + member1.getTeam().getClass());
            tx.commit();

        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
