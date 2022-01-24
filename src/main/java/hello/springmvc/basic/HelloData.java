package hello.springmvc.basic;

import lombok.*;

@Data
// @Data 는 밑의 어노테이션의 기능을 자동으로 적용해준다. 외에도 하는 일이 있음
// @Getter @Setter @RequiredArgsConstructor @EqualsAndHashCode @ToString 등등
public class HelloData {

    private String username;
    private int age;
}
