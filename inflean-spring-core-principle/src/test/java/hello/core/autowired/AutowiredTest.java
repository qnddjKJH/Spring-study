package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        // 3가지

        // 자동 주입 대상이 없으면 수정자 메소드 자체가 호출 x
        @Autowired(required = false) // 기본 값 true 이면 터진다.
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1); // 호출이 자체가 안되므로 출력이 안됨
        }

        // 자동 주입 대상이 없으면 null 입력
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2); // 호출 자체는 되어서 출력이 되지만 값은 null 이다.
        }

        // Optional.empty 가 입력
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3); // 마찬가지로 호출은 되지만 입력은 Optional.empty 가 값으로 입력된다.
        }


    }
}
