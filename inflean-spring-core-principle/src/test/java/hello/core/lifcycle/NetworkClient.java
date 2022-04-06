package hello.core.lifcycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient /*implements InitializingBean, DisposableBean*/ {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    /** @PostConstruct, @PreDestroy 특징
     * 최신 스프링에서 가장 권장하는 방법이다.
     * 애노테이션 하나만 붙이면 되므로 매우 편리하다.
     * import 패키지명을 잘 보면 'javax.annotation.PostConstruct' 이다. 즉 스프링에 종속된 것이 아닌
     * JSR-250 라는 표준이다. 따라서 스프링이 아닌 다른 컨테이너에서도 동작한다.!
     * 컴포넌트 스캔과 잘 어울린다.
     *
     * 유일한 단점은 외부 라이브러리에는 적용이 불가능하다. 외부 라이브러리의 초기화와 종료는
     * @Bean 의 기능을 사용하자.
     */
    // 직접 빈 등록 초기화, 소멸 메서드
    // 메서드를 작성하고, 빈 설정하는 곳에 가서
    // @Bean(initMethod = " 초기화 메서드 명 ", destroyMethod = " 소멸 메서드 명"
    // 으로 초기화, 소멸 메서드를 등록한다.
    @PostConstruct  // 애노테이션 단 한 줄이면 라이프 생명주기 콜백이 지정된다
    public void init() {
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy // 애노테이션 단 한 줄이면 라이프 생명주기 콜백이 지정된다
    public void close() {
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
    /** 설정 정보 사용 특징
     *
     *  메서드 이름을 자유롭게 줄 수 있다.
     *  스프링 빈이 스프링 코드에 의존하지 않는다.
     *  코드가 아니라 설정 정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화,
     *  종료 메서드를 적용할 수 있다.
     */

    // implements 된 메서드 이름 부터가 'after'PropertiesSet 이다
    // properties 가 설정된 후 인것이다.
    // 스프링이 의존관계 주입 된 후 실행 된다.
    // Bean 생성되고 의존관계 주입 된 후
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }

    // Bean 이 종료(소멸) 될 때
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }
}

/** 초기화, 소멸 인터페이스의 단점
 * 이 인터페이스는 '스프링 전용 인터페이스' 이다. 해당 코드가 스프링 전용 인터페이스에 의존한다.
 * 초기화, 소멸 메서드의 이름을 변경이 불가능하다.
 * 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.
 *
 * 스프링 초창기에 나온 방법들이다.
 * 지금은 더 나은 방법이 많아 더는 사용하지 않는다.
 */
