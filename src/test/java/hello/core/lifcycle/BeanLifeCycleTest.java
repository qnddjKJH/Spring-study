package hello.core.lifcycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
        // ApplicationContext 를 닫아야 하는데 기본 ApplicationContext 는 제공하지 않는다.
        // AnnotationConfigContext 나 ConfigurableApplicationContext 로 바꿔야한다.
        // 상위 ApplicationContext -> ConfigurableApplicationContext -> AnnotationConfigApplicationContext 하위

    }

    @Configuration
    static class LifeCycleConfig {

        @Bean // (initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); // 넘겨주는 설정이 없어 null 이 뜬다.
            networkClient.setUrl("http://hello-spring.dev");
            // 객체를 생성한 후에 설정이 들어올 수도 있다.
            return networkClient;
        }
    }
}

// 스프링 빈은 간단하게 다음과 같은 라이프 사이클을 가진다.
// 객체 생성 -> 의존관계 주입

/** 참고 : 객체의 생성과 초기화를 분리하자
 *
 * 의존주입과는 다르다.
 * SRP (Single Responsibility Principle: 단일 책임 원칙)
 *  - 단일 클래스는 오직 하나의 일(책임)을 가져야 한다. 단일 클래스는 오직 한 가지 일에만
 *  책임이 있어야 합니다. 하나 이상의 책임이 있다면, 이것은 결합(Coupled)를 불러옵니다.
 *  하나의 책임에 대한 변경은 다른 책임의 수정을 발생시킵니다.
 *
 *  단일 책임 원칙에 따라 객체 생성에는 객체를 생성하는데 집중하자
 *  따라서 생성자 안에서 무거운 초기화 작업을 함께 하는 것보다 객체를 생성하는 부분과 초기환 하는
 *  부분을 명확하게 나누는 것이 '유지보수 관점'에서 좋다.
 *
 *  물론 초기화 작업이 내부 값들만 약간 변경하는 정도로 단순한 경우에는 생성자에서 한번에 다 처리하는
 *  것이 더 나을 수도 있다.
 */
