package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    // 배열로 다중 설정 가능
    // 메서드 설정이 없으면 모든 HTTP 요청 메서드로 접속 가능 (GET, POST, PUT ...등)
    @RequestMapping({"/hello-basic", "/hello-go"})
    public String helloBasic() {
        log.info("helloBasic");

        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    /**
     * 편리한 축약 어노테이션
     *
     * @GetMapping
     * @PostMapping
     * @PutMapping
     * @UpdateMapping
     * @DeleteMapping 축약 어노테이션 들어가 보면 결국 RequestMapping 이다.
     */
    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2() {
        log.info("mappingGetV2");
        return "ok";
    }

    /**
     * @PathVariable 사용
     * 변수명이 같으면 생략 가능
     * @PathVariable("userId") String data -> @PathVariable String userId
     * /mapping/userA
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params = "mode",
     * params = "!mode",
     * params = "mode=debug",
     * params = "mode!=debug",
     * params = {"mode = debug", "data=good"}
     */
    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam() {
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    // Content-Type 별로 처리 할 때에는 headers 를 쓰면 안되고 consumes 를 써야한다.
    // Content-Type -> consumes = 소비, Accept -> produces = 생산
    @PostMapping(value = "/mapping-consunme", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    // Accept : text/html --> text/html 타입을 나는 받아들일 수 있어!
    @PostMapping(value = "/mapping-produce", produces = MediaType.TEXT_HTML_VALUE)
    public String mappingProduce() {
        log.info("mappingProduce");
        return "ok";
    }
}
