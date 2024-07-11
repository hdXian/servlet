package hdxian.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--- new request has occurred ---");
        printStartLine(request);
        printHeaders(request);
        printHeadersUtils(request);
        printEtc(request);
    }

    private void printStartLine(HttpServletRequest request) {
        System.out.println("--- start of REQUEST LINE ----");

        System.out.println("request.getMethod(): " + request.getMethod()); // GET, POST, DELETE, ...

        System.out.println("request.getProtocol(): " + request.getProtocol()); // HTTP/1.1, ...

        System.out.println("request.getScheme(): " + request.getScheme()); // http

        System.out.println("request.getRequestURL(): " + request.getRequestURL()); // http://localhost:8080/request-header

        System.out.println("request.getRequestURI(): " + request.getRequestURI()); // /request-header

        System.out.println("request.getQueryString(): " + request.getQueryString()); // query string (parameter)

        System.out.println("request.isSecure(): " + request.isSecure()); // false: http, true: https

        System.out.println("--- end of REQUEST LINE ---\n");
    }


    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- start of REQUEST HEADER ----");

        // 방법 1
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while (headerNames.hasMoreElements()) {
//            String headerName = headerNames.nextElement();
//            System.out.println(headerName + " : " + request.getHeader(headerName));
//        }

        // 방법 2 (더 간결한 방식)
        request.getHeaderNames().asIterator()
                        .forEachRemaining(headerName -> System.out.println(headerName + " : " + request.getHeader(headerName)));

        System.out.println("--- end of REQUEST HEADER ---\n");
    }

    private void printHeadersUtils(HttpServletRequest request) {
        System.out.println("--- start of REQUEST HEADER (more utility) ----");

        System.out.println("서버 정보 조회");
        System.out.println("request.getServerName(): " + request.getServerName());

        System.out.println("request.getServerPort(): " + request.getServerPort());

        System.out.println("Accept-Language 조회");
        request.getLocales().asIterator()
                        .forEachRemaining(lang -> System.out.println("locale: " + lang));

        System.out.println("request.getLocale(): " + request.getLocale());
        System.out.println();

        System.out.println("cookie 조회");
        if (request.getCookies() != null) {
            for(Cookie cookie: request.getCookies())
                System.out.println(cookie.getName() + " : " + cookie.getValue());
        }
        System.out.println();

        System.out.println("Content 관련 조회");
        System.out.println("request.getContentType(): " + request.getContentType());

        System.out.println("request.getContentLength(): " + request.getContentLength());

        System.out.println("request.getCharacterEncoding(): " + request.getCharacterEncoding());

        System.out.println("--- end of REQUEST HEADER (more utility) ---\n");
    }

    private void printEtc(HttpServletRequest request) {
        // http 메시지 정보는 아님.
        System.out.println("--- start of etc info ----");

        System.out.println("Remote info"); // 요청 보낸 쪽 정보
        System.out.println("request.getRemoteHost(): " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr(): " + request.getRemoteAddr());
        System.out.println("request.getRemotePort(): " + request.getRemotePort());

        System.out.println("Local info"); // 요청 받은 쪽 정보
        System.out.println("request.getLocalName(): " + request.getLocalName());
        System.out.println("request.getLocalAddr(): " + request.getLocalAddr());
        System.out.println("request.getLocalPort(): " + request.getLocalPort());

        System.out.println("--- end of etc info ---\n");
    }

}
