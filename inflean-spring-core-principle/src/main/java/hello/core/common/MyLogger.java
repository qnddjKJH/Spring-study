package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

// 로그를 출력하기 위한 MyLogger 클래스
// @Scope("request") 가능하지만 proxyMode 옵션을 사용 못한다.
// proxyMode 는 사용하면  Provider 를 사용하는 긴 코드보다 간결하게 사용하기 위해 Provider 사용을 하지 않는다)
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;
    // requestURL 은 이 빈이 생성되는 시점을 알 수 없으므로, 외부에서 setter 로 message 를 받는다.

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "] " + "[" + requestURL + "] " + message );
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString(); // 글로벌하게 제일 유니크한 아이디가 생성된다 절대 겹치지 않음.
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
