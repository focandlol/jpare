package studyre.querydslre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import studyre.querydslre.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member,Long>, MemberRepositoryCustom{

    List<Member> findByUsername(String username);
}
