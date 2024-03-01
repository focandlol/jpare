package jpare.datajpare.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpare.datajpare.repository.MemberRepository;
import jpare.datajpare.repository.MemberRepositoryCustom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void testEntity(){
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
        for (Member member : members) {
            System.out.println("member = " + member);
            System.out.println("member.getTeam = " + member.getTeam());
        }
    }

    @Test
    public void jpaEventBaseEntity() throws InterruptedException {
        Member member = new Member("member1");
        memberRepository.save(member);

        Thread.sleep(100);
        member.setUsername("member2");

        em.flush();
        em.clear();

        Member findMember = memberRepository.findById(member.getId()).get();

        System.out.println("findMemberc = " + findMember.getCreatedDate());
        System.out.println("findMemberu = " + findMember.getLastModifiedDate());
        System.out.println("findMembercb = " + findMember.getCreatedBy());
        System.out.println("findMemberub = " + findMember.getLastModifiedBy());
    }
}