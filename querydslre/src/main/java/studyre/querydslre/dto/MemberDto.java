package studyre.querydslre.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.annotation.sql.DataSourceDefinition;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {

    private String username2;

    private int age;

    @QueryProjection
    public MemberDto(String username2, int age) {
        this.username2 = username2;
        this.age = age;
    }
}
