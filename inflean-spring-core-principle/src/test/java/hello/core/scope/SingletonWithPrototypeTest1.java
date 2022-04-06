package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        // 생성시점에 주입된 프로토타입 빈이 스프링 컨테이너에서 불러와진다.
        // 우리가 원하는것은 이게 아니다. 호출 시점에 생성이 되는 것을 원한다.
        // 가장 간단한 방법은 싱글톤 빈이 프로토타입을 사용할 때 마다 스프링 컨테이너에 새로 요청하는 것
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    @Scope("singleton")
    static class ClientBean {
//        private final PrototypeBean prototypeBean;  // 생성시점에 주입

//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean) {
//            this.prototypeBean = prototypeBean;
//        }

//        가장 간단한 방법은 싱글톤 빈이 프로토타입을 사용할 때 마다 스프링 컨테이너에 새로 요청하는 것
//        주석 처리 부분이 해당 코드이다. 해당 코드 사용시 위의 의존 주입 대신 해당 코드로 바꿔야함
//        하지만 이렇게 하는 경우 스프링의 애플리케이션 컨텍스트 전체를 주입받게 되면,
//        스프링 컨테이너에 종속적인 코드가 되고, 단위 테스트도 어려워진다.
//        지금 필요한 기능은 지정한 프로토타입 빈을 컨테이너에서 대신 찾아주는 딱 "DL" 정도의 기능만
//        제공하는 무언가가 있으면 된다 (DL = Dependency Lookup)
//        @Autowired
//        private ApplicationContext ac;

        // ObjectFactory 가 먼저 만들어졋고 편의기능을 추가한게 ObjectProvider 이다.
        // Provider(제공해주는 자) Provider 가 찾아주는 기능만 제공해 준다
        // 스프링이 제공하는 기능을 사용하지만, 기능이 단순하므로 단위테스트를 만들거나 mock 코드를 만들기 훨씬 쉬워진다.
        @Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;

        // javax.inject.Provider 사용 라이브러리가 필요하다 => javax.inject:javax.inject:1 >> gradle 추가
//        @Autowired
//        private Provider<PrototypeBean> prototypeBeanProviderInject;

        public int logic() {
//          PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
//            PrototypeBean prototypeBean1 = prototypeBeanProviderInject.get();
            // 호출하면 내부에서는 스프링 컨테이너를 통해 해당 빈을 찾아서 반환한다.
            // Prototype scope 의 빈이기 때문에 스프링 컨테이너에 요청하면 요청된 그 시점에서 생성해서 반환해준다. => DL
            prototypeBean.addCount();
//            prototypeBean1.addCount();
            int count = prototypeBean.getCount();

            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private  int count = 0;

        public void addCount() {
            count++;
        }
        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy ");
        }
    }


}

