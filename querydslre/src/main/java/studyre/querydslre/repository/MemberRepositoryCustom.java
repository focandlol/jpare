package studyre.querydslre.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import studyre.querydslre.dto.MemberSearchCond;
import studyre.querydslre.dto.MemberTeamDto;
import studyre.querydslre.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

    List<MemberTeamDto> search(MemberSearchCond cond);

    Page<MemberTeamDto> searchPageSimple(MemberSearchCond cond, Pageable pageable);
    Page<MemberTeamDto> searchPageComplex(MemberSearchCond cond, Pageable pageable);
}
