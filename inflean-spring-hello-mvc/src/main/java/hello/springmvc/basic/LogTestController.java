package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
*   장점
*   1. 쓰레드 정보, 클래스 이름 부가 정보를 함께 볼 수 있고, 출력 모양 지정 가능
*   2. 로그 레벨에 따라 개발 서버에서는 모든 로그를 출력하고, 운영서버에서는 출력
*   하지 않는 등 로그를 상황에 맞춰 조절 가능
*   3. 시스템 아웃 콘솔에만 출력하는 것만이 아니라, 파일이나 네트워크 등 로그를 별도의
*   위치에 남길 수 있다. 특히 파일로 남길 때는 일별, 특정 용량에 따라 로그를 분할 하는 것도 가능하다.
*   4. 성능도 일반 System.out 보다 좋다 (내부 버퍼링, 멀티 쓰레드 등) 그래서 실무에서는 꼭 로그 사용!
*/

@Slf4j
@RestController
public class LogTestController {
    // private final Logger log = LoggerFactory.getLogger(LogTestController.class);
    // (getClass()) 로 자기 자신을 불러도 된다.
    // @Slf4j 로 간단하게 사용 가능.

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        // log 를 찍을 때 레벨을 지정할 수 있다. (레벨 깊이가 높은 순서로 적어둠)
        // LEVEL = TRACE > DEBUG > INFO > WARN > ERROR
        // 개발 서버는 debug 출력, 운영 서버는 info 출력
        // properties 에서 레벨 설정 안하면 trace, debug 는 찍히지 않음
        // properties 에서 레벨을 설정 할 수 있다.
        // 자주 쓰는건 info 레벨
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);
        // 사용은 {} , value 로 포멧 시킨다.
        // 자바 처럼 + 하면 의미없는 연산이 발생하고 로그는 수십곳에서 찍히므로
        // + 로 사용하면 안된다!

        return "ok";
        // @Controller 이면 viewName 이 반환되는것 = view 를 찾아서 view 를 반환
        // @RestController 이면 HTTP message body 에 바로 입력 한다.
        // REST API 를 만드는 데 핵심적인 기능임
    }
}
