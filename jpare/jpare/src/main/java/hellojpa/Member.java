package hellojpa;

import jdk.jfr.Name;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Member{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(name = "username")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Embedded
    private Period workPeriod;

    @Embedded
    private Address homeAddress;

    @ElementCollection
    @CollectionTable(name = "favorite_foods",joinColumns =
            @JoinColumn(name = "member_id")
    )
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "member_id")
    private List<AddressHistory> addressHistory = new ArrayList<>();

    //@ElementCollection
   // @CollectionTable(name = "address",joinColumns =
   // @JoinColumn(name = "member_id"))
   // private List<Address> addressHistory = new ArrayList<>();

    /*@Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
            column=@Column(name="work_city")),
            @AttributeOverride(name="street",
                    column=@Column(name="work_street")),
            @AttributeOverride(name="zipcode",
                    column=@Column(name="work_zipcode"))
    })
    private Address workAddress;*/



    //@OneToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "locker_idE")
    //private Locker locker;


    public Member() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<AddressHistory> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<AddressHistory> addressHistory) {
        this.addressHistory = addressHistory;
    }
}
