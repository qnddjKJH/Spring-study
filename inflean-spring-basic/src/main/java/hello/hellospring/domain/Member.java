package hello.hellospring.domain;

import javax.persistence.*;

// @Entity = jpa 가 관리하는 Entity
@Entity
public class Member {

    // java 에선 Long type db 에서는 bigint type이다
    // 전략 = DB가 알아서 생성 하는 타입 IDENTITY
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
