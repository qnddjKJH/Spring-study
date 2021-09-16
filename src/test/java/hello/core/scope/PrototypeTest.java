package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

/** 프로토타입 빈의 특징 정리
 * 스프링 컨테이너에 요청할 때 마다 새로 생성된다.
 * 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입 그리고 초기화까지만 관여한다.
 * 종료 메서드가 호출되지 않는다.
 * 그래서 프로토타입 빈은 프로토타입 빈을 조회한 클라이언트가 관리해야 한다.
 * 종료 메서드에 대한 호출도 클라이언트가 직접 해야한다.
 *
 * 프로토타입 빈은 어쩌다 한 번 쓸까 말까함
 */

public class PrototypeTest {
    @Test
    void prototypeBeanTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("PrototypeBean1 = " + prototypeBean1);
        System.out.println("PrototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        //ac.close();
        // 배운대로 두 빈은 완전히 다른 빈, 2번 조회했으므로 2번 생성되고 2번 초기화 된다.
        // 그리고 소멸 메서드가 동작하지 않는다
        // 그래서 적절하게 다 쓰고 난뒤에 직접 소멸 메서드를 호출해 주어야 한다.

        prototypeBean1.destroy();
        prototypeBean2.destroy();
        ac.close();
    }

    // @Component 가 없는 이유는 위의 AnnotationConfigApplicationContext 에 클래스로 지정해 주면
    // 이 클래스 자체가 컴포넌트 스캔 대상처럼 동작하기 때문에 이 클래스를 빈으로 등록한다.
    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
