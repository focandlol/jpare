package jpare.datajpare.repository;

import jpare.datajpare.domain.Member;
import jpare.datajpare.domain.Team;
import jpare.datajpare.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    TeamRepository teamRepository;


    @Test
    public void basicCRUD(){
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);
    }

    @Test
    public void findByUsernameAndAgeGreaterThan(){
        Member m1 = new Member("aaa", 10);
        Member m2 = new Member("aaa", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("aaa", 15);
        List<Member> aaa = memberRepository.findHelloByUsername("aaa");

        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findHelloBy(){
        List<Member> helloBy = memberRepository.findHelloBy();
    }

    @Test
    public void namedQuery(){
        Member m1 = new Member("aaa", 10);
        Member m2 = new Member("bbb", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsername("AAA");
        Member findMember = result.get(0);

    }

    @Test
    public void testQuery(){
        Member m1 = new Member("aaa", 10);
        Member m2 = new Member("bbb", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findUser("aaa",10);
        assertThat(result.get(0)).isEqualTo(m1);

    }

    @Test
    public void findUsernameList(){
        Member m1 = new Member("aaa", 10);
        Member m2 = new Member("bbb", 20);

        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> result = memberRepository.findUsernameList();
        assertThat(result.get(0)).isEqualTo("aaa");

    }

    @Test
    public void findMemberDto(){
        Team teamA = new Team("teamA");

        Member m1 = new Member("aaa", 10);
        m1.changeTeam(teamA);
        memberRepository.save(m1);
        teamRepository.save(teamA);


        List<MemberDto> result = memberRepository.findMemberDto();
        for (MemberDto memberDto : result) {
            System.out.println("memberDto = " + memberDto);
        }

    }

}