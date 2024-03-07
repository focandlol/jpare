package studyre.querydslre.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import studyre.querydslre.dto.MemberSearchCond;
import studyre.querydslre.dto.MemberTeamDto;
import studyre.querydslre.repository.MemberJpaRepository;
import studyre.querydslre.repository.MemberRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberJpaRepository memberJpaRepository;
    private final MemberRepository memberRepository;
    @GetMapping("/v1/members")
    public List<MemberTeamDto> searchMemberV1(MemberSearchCond condition) {
        return memberJpaRepository.search(condition);
    }

    @GetMapping("/v2/members")
    public Page<MemberTeamDto> searchMemberV2(MemberSearchCond condition, Pageable pageable) {
        return memberRepository.searchPageSimple(condition,pageable);
    }

    @GetMapping("/v3/members")
    public Page<MemberTeamDto> searchMemberV3(MemberSearchCond condition, Pageable pageable) {
        return memberRepository.searchPageComplex(condition,pageable);
    }
}
