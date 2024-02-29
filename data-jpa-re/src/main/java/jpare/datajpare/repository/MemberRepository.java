package jpare.datajpare.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import jpare.datajpare.domain.Member;
import jpare.datajpare.dto.MemberDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findHelloBy();

    List<Member> findHelloByUsername(String username);

    @Query(name = "Member.findByUsername")
    List<Member> findByUsername(@Param("username") String username);

    @Query("select m from Member m where m.username = :username and m.age =:age")
    List<Member> findUser(@Param("username") String username, @Param("age")int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new jpare.datajpare.dto.MemberDto(m.id,m.username,t.name)from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    List<Member> findListByUsername(String username);
    Member findMemberByUsername(String username);

    Optional<Member> findOptionalByUsername(String username);

    Page<Member> findByAge(int age, Pageable pageable);

    Slice<Member> findSliceByAge(int age, Pageable pageable);

    @Query(value = "select m from Member m left join m.team t",
            countQuery = "select count(m) from Member m")
    Page<Member> findQueryByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true) //자동 em.clear()
    @Query("update Member m set m.age = m.age+1 where m.age > :age")
    int bulkAgePlus(@Param("age") int age);

    @QueryHints(
        value = @QueryHint(name = "org.hibernate.readOnly",value = "true")
    )
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);
}
