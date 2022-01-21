package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
* 실행되는 순서
* 1. HandlerMapping 핸들러 매핑에서 우선순위에 따른 컨트롤러를 찾는다
*   0 순위 = RequestMappingHandlerMapping : 어노테이션 기반의 컨트롤러
*   1 순위 = BeanNameUrlHandlerMapping : 스프링 빈의 이름으로 핸들러를 찾는다.
* 2. HandlerAdapter 핸들러 매핑에서 찾은 핸들러를 실행할 수 있는 핸들러 어뎁터를 찾느다.
*   0 순위 = RequestMappingHandlerAdapter : 어노테이션 기반의 컨트롤러
*   1 순위 = HttpRequestHandlerAdapter : HttpRequestHandler 처리
*   2 순위 = SimpleControllerHandlerAdapter : Controller 인터페이스(어노테이션 x, 과거에 사용)
*           SimpleControllerHandlerAdapter -> old.OldController 예제
*/

// 컴포넌트 스캔에 대상 지정 및 빈의 이름 등록
@Component("/springmvc/request-handler") 
public class MyHttpRequestHandler implements HttpRequestHandler {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MyHttpRequestHandler.handleRequest");
    }
}
