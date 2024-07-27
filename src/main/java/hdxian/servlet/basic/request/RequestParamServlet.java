package hdxian.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// 1. query parameter function
// example:
// http://localhost:8080?username=gdh&age=19
//
// 2. it's possible that send duplicate query parameter
// example:
// http://localhost:8080?username=gdh&age=19&username=gdh2

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("RequestParamServlet.service");

        System.out.println("전체 파라미터 조회 시작");
        request.getParameterNames().asIterator() // parameterName: Key, parameter: Value
                .forEachRemaining(parameterName -> System.out.println(parameterName + ": " + request.getParameter(parameterName)));
        System.out.println("전체 파라미터 조회 끝");
        System.out.println();

        System.out.println("단일 파라미터 조회");
        // 여러 값을 가진 파라미터도 내부 우선순위에 따라 하나만 조회
        System.out.println("username: " + request.getParameter("username"));
        System.out.println("age: " + request.getParameter("age"));
        System.out.println();

        // 같은 키에 여러 값을 전달할 수 있다.
        // 거의 쓰지는 않는다.
        System.out.println("여러 값을 가진 파라미터 조회");
        String[] usernames = request.getParameterValues("username");
        for(String name: usernames)
            System.out.println("username: " + name);

        response.getWriter().write("request successfully accepted. check the server console.");
        System.out.println();

    }
}
