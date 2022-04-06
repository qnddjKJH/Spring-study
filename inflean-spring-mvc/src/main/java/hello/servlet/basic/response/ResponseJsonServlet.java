package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Content-Type: application/json
        // 마찬가지로 HTTP 응답으로 JSON 반환시 content-type 을 "application/json" 을 지정
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        // application/json 은 스펙상 utf-8 형식을 사용하도록 권장되고 있다
        // 즉, application/json;charset=utf-8 로 전달하는 것은 의미없는 파라미터를 추가한 것
        // response.getWriter() 를 사용하면 추가 파라미터를 자동으로 추가해버린다.
        // 이때는 response.getOutputStream() 으로 출력하면 그런 문제는 없어진다.

        HelloData helloData = new HelloData();
        helloData.setUsername("kim");
        helloData.setAge(20);

        // { "username":"kim", "age" : 20 }
        String result = objectMapper.writeValueAsString(helloData);
        response.getWriter().write(result);
    }
}
