package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody // return 값을 Http 응답 바디에 넣어 준다. -> RestController 와 같음
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge) {
        log.info("username = {}, age = {}", memberName, memberAge);

        return "ok";
    }

    @ResponseBody // return 값을 Http 응답 바디에 넣어 준다. -> RestController 와 같음
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) {
        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    @ResponseBody // return 값을 Http 응답 바디에 넣어 준다. -> RestController 와 같음
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        // String, int 등의 단순 타입이면 @RequestParam 마저도 생략이 가능...ㄷ
        // 주관적으로 어노테이션은 쓰는게 나아 보임 (명확함)
        log.info("username = {}, age = {}", username, age);

        return "ok";
    }

    // 필수 파라미터 여부

    /**
     * @RequestParam.required /request-param -> username이 없으므로 예외
     *
     * 주의!
     * /request-param?username= -> 빈문자로 통과
     *
     * 주의!
     * /request-param
     * int age -> null을 int에 입력하는 것은 불가능, 따라서 Integer 변경해야 함(또는 다음에 나오는
     * defaultValue 사용)
     */
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {
        // age 가 Integer 인 이유는 int 는 null 이 될 수가 없기 때문에 wrapper class 사용
        // int 로 만들려면 required 옵션을 true 로 줘야겠죠? 안넣어도 필수지만
        // null 과 "" 는 다르다 파라미터 이름만 있으면 빈문자열이 값으로 들어가서 통과된다.
        // 그리고 required 는 기본값이 true 이므로 안넣는게 나음
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    /**
     * @RequestParam
     * - defaultValue 사용
     *
     * 참고: defaultValue는 빈 문자의 경우에도 적용
     * /request-param?username=
     */
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        // int 로 만들고 필수 여부를 false 주면 null 이 안들어가지만
        // 기본값을 주어서 null 이 들어가는 에러를 막을 수 있다.
        // 결과적으로 defaultValue 를 주면 required 옵션이 무의미 해진다.
        // defaultValue 는 "" 빈문자열도 감지해서 기본값으로 넣어준다
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        // MultiValueMap 은 같은 키 값에 여러 value 가 담길 수 있다.
        // 파라미터의 값은 보통 1개를 쓴다 애매하게 2개를 쓰는 경우가 없기 때문에 사용할 일이 많을까?..
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }


    /**
     * @ModelAttribute 사용
     * 참고: model.addAttribute(helloData) 코드도 함께 자동 적용됨, 뒤에 model을 설명할 때
    자세히 설명
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(/*@RequestParam String username, @RequestParam int age*/
            @ModelAttribute HelloData data){
//        HelloData data = new HelloData();
//        data.setUsername(username);
//        data.setAge(age);
        // age=abc 처럼 숫자가 들어가야 할 곳에 문자를 넣으면 BindingException 이 발생
        // Binding 오류는 검증 부분에서 다룬다.
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }

    /**
     * @ModelAttribute 생략 가능
     * String, int 같은 단순 타입 = @RequestParam
     * argument resolver 로 지정해둔 타입 외 = @ModelAttribute
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData data) {
        // @ModelAttribute 생략시 다음 규치 적용
        // String, int, Integer 등 단순 타입...@RequestParam 과 동일
        // 나머지 = @ModelAttribute (argument resolver 로 지정해둔 타입 외)
        // argument resolver 예) HttpServletRequest 같은 거
        log.info("username={}, age={}", data.getUsername(), data.getAge());
        return "ok";
    }
}