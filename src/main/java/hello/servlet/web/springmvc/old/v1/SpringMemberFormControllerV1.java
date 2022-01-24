package hello.servlet.web.springmvc.old.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Component    // 컴포넌트 스캔의 자동 대상등록
//@RequestMapping  // 이거만 쓸려면 스프링 빈으로 직접 등록해야한다.
@Controller
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
