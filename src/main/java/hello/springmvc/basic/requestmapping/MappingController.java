package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
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
     * */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data) {
        log.info("mappingPath userId={}", data);
        return "ok";
    }
}
