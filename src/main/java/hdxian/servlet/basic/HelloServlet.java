package hdxian.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    // 해당 클래스에 매핑된 url이 호출되면 서블릿 컨테이너가 service() 메서드를 실행한다.
    // 비슷한 시그니처의 service() 주의 (protected가 아님)
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");

        // http 서블릿 표준 기반의 구현체
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // 서블릿을 이용하면 쿼리 파라미터 등 요청 정보를 쉽게 다룰 수 있다.
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // 응답 메시지 만들기

        // http header
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8"); // EUCKR은 오래된 인코딩. 사용하지 말 것.

        // http body
        response.getWriter().write("hello " + username);

    }
}
