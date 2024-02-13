package jpabookre.jpashopre.service;

import jpabookre.jpashopre.domain.Member;
import jpabookre.jpashopre.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입(){
        Member member = new Member();
        member.setName("kim");

        Long saveId = memberService.join(member);

        assertEquals(member,memberService.findOne(saveId));
    }

    @Test
    public void 중복회원예외(){

        Member member1 = new Member();
        member1.setName("km1");

        Member member2 = new Member();
        member2.setName("km1");

        memberService.join(member1);

        assertThrows(IllegalArgumentException.class,
                () -> memberService.join(member2));
    }



}