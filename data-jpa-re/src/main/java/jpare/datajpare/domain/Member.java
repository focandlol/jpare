package jpare.datajpare.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.IdentityHashMap;

@Entity
@Getter
@Setter
@ToString(of = {"id","username","age"})
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String username;

    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team!=null) {
            changeTeam(team);
        }
    }

    public void changeTeam(Team team){
        this.team = team;
        team.getMemberList().add(this);
    }
}
