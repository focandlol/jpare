package studyre.querydslre.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;
import studyre.querydslre.dto.MemberSearchCond;
import studyre.querydslre.dto.MemberTeamDto;
import studyre.querydslre.dto.QMemberTeamDto;
import studyre.querydslre.entity.Member;

import java.util.ArrayList;
import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.count;
import static com.querydsl.core.types.ExpressionUtils.in;
import static studyre.querydslre.entity.QMember.member;
import static studyre.querydslre.entity.QTeam.team;

public class MemberRepositoryImpl extends QuerydslRepositorySupport implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /*
    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }*/

    public MemberRepositoryImpl(EntityManager em) {
        super(Member.class);
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<MemberTeamDto> search(MemberSearchCond cond){
        OrderSpecifier<Long> desc = member.id.desc();
        List<Long> a = new ArrayList<>();
        from(member)
                .leftJoin(member.team,team)
                .where(usernameEq(cond.getUsername()),
                        teamNameEq(cond.getTeamName()),
                        ageGoe(cond.getAgeGoe()),
                        ageLoe(cond.getAgeLoe())
                )
                .select(new QMemberTeamDto(
                        member.id,
                        member.username,
                        member.age,
                        team.id,
                        team.name)
                ).fetch();

        return queryFactory
                .select(new QMemberTeamDto(
                        member.id,
                        member.username,
                        member.age,
                        team.id,
                        team.name
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(cond.getUsername()),
                        teamNameEq(cond.getTeamName()),
                        ageGoe(cond.getAgeGoe()),
                        ageLoe(cond.getAgeLoe())
                )
                .orderBy(desc)
                .fetch();


    }

    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCond cond, Pageable pageable) {
        QueryResults<MemberTeamDto> results = queryFactory
                .select(new QMemberTeamDto(
                        member.id,
                        member.username,
                        member.age,
                        team.id,
                        team.name
                ))
                .from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(cond.getUsername()),
                        teamNameEq(cond.getTeamName()),
                        ageGoe(cond.getAgeGoe()),
                        ageLoe(cond.getAgeLoe())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy()
                .fetchResults();


        List<MemberTeamDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl<>(content,pageable,total);
    }


    public Page<MemberTeamDto> searchPageSimpleSupport(MemberSearchCond cond, Pageable pageable) {
        JPQLQuery<MemberTeamDto> jpaQuery = from(member)
                .leftJoin(member.team, team)
                .where(
                        usernameEq(cond.getUsername()),
                        teamNameEq(cond.getTeamName()),
                        ageGoe(cond.getAgeGoe()),
                        ageLoe(cond.getAgeLoe())
                )
                .select(new QMemberTeamDto(
                        member.id,
                        member.username,
                        member.age,
                        team.id,
                        team.name
                ));

        JPQLQuery<MemberTeamDto> query = getQuerydsl().applyPagination(pageable, jpaQuery);
        QueryResults<MemberTeamDto> con = query.fetchResults();

        List<MemberTeamDto> content = con.getResults();
        long total = con.getTotal();

        return new PageImpl<>(content,pageable,total);
    }

    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCond condition,
                                                 Pageable pageable) {
        List<MemberTeamDto> content = queryFactory
                .select(new QMemberTeamDto(
                        member.id,
                        member.username,
                        member.age,
                        team.id,
                        team.name))
                .from(member)
                .leftJoin(member.team, team)
                .where(usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Member> countQuery = queryFactory
                .select(member)
                .from(member)
                .leftJoin(member.team, team)
                .where(usernameEq(condition.getUsername()),
                        teamNameEq(condition.getTeamName()),
                        ageGoe(condition.getAgeGoe()),
                        ageLoe(condition.getAgeLoe()));

        return PageableExecutionUtils.getPage(content,pageable,() -> countQuery.fetch().size());
        //return new PageImpl<>(content, pageable, total);
    }

    private BooleanExpression usernameEq(String username) {
        return StringUtils.hasText(username) ? member.username.eq(username) : null;
    }

    private BooleanExpression teamNameEq(String teamName) {
        return StringUtils.hasText(teamName) ? team.name.eq(teamName) : null;
    }

    private BooleanExpression ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private BooleanExpression ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }
}
