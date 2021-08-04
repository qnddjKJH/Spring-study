package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "조금 시간이 걸리네요!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // RequestParm url의 ? 이후의 값을 가지고 오는거 express 서버 공부할때 받는거랑 같음
        // "name"에 해당하는 parameter 를 String name 에 저장
        model.addAttribute("name", name);
        // model 에 "name" 이라는 이름으로 name 값을 저장하여 보냅니다.
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
        // http body부에 "hello " + name 데이터 값을 직접 넣어주겠다는 메소드
        // 페이지 소스 보기를 할 경우 html 코드는 1절 없기
        // hello name(값) 이 적혀있다.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // 페이지 소스 보기를 할 경우 JSON 형식으로 나옴.
    }

    static class Hello {
        // static 객체로 만들면 클래스 안에서 이 클래스를 사용가능해진다.
        // HelloController.Hello 이게 가능해짐 자바에서 정식으로 지원하는 문법
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
