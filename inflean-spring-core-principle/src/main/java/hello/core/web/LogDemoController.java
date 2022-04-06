package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;

    // proxyMode 를 사용하여 Provider 를 사용하지 않게 코드를 작성할 수 있다.
    private final MyLogger myLogger;    // myLogger 가 proxyMode 에 의해서 실제 myLogger 가 아니다.
    // CGLIB 라는 라이브러리로 내 클래스를 상속받는 가짜 프록시 객체를 만들어서 주입한다.
    // ㄴ바이트 코드를 조작하는 라이브러리이다
    // ac.getBean("myLogger", MyLogger.class) 로 조회해도 가짜 프록시 객체가 조회된다
    // 그래서 의존관계 주입도 이 가짜 프록시 객체가 주입된다.

    // private final ObjectProvider<MyLogger> myLoggerProvider;
    // ObjectProvider 덕분에 ObjectProvider.getObject()
    // 를 호출하는 시점까지 request scope 빈의 생성을 지연 할 수 있다.

    // LogDemoController, LogDemoService 에서 각각 한번씩 따로 호출해도
    // 같은 HTTP 요청이면 같은 스프링 빈이 반환된다. -> 직접 구현할려면 너무...너무...힘듬...

    // 핵심은 각 요청마다 객체를 관리해주는 것
    // 아무리 요청이 많아도 각각 관리 해준다.
    @RequestMapping("log-demo")
    @ResponseBody // 문자를 그대로 응답 보낼 수 있음
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();

        // MyLogger myLogger = myLoggerProvider.getObject();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL); // 빈 생성 시점을 모르는 requestURL 에 set 으로 url 넘김

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }
}
