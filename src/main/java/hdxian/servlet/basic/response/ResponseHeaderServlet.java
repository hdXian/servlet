package hdxian.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // status line
        response.setStatus(HttpServletResponse.SC_OK); // 200 OK

        // auto generate default charset if there are no charset.
        response.setHeader("Content-Type", "text/plain;charset=UTF-8");

        // disable cache
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");

        // custom header
        response.setHeader("my-header", "hello");

        // order header settings
        content(response);
        cookie(response);
        redirect(response);

        // message body
        PrintWriter writer = response.getWriter();
        writer.println("ok body");
        writer.println("한글 응답 메시지");
    }

    // content setting util
    private void content(HttpServletResponse response) {
        // Content-Type: text/plain;charset=utf-8

//        response.setHeader("Content-Type", "text/plain;charset=UTF-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
//        response.setContentLength(9); // auto generated
    }

    // cookie setting func
    private void cookie(HttpServletResponse response) {
        // Set-Cookie: myCookie=good; Max-Age=600;

//        response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); // 600 seconds
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException {
        // Status: 302 found
        // Location: /basic/redirect.html

//        response.setStatus(HttpServletResponse.SC_FOUND); // 302 found (redirect)
//        response.setHeader("Location", "/basic/redirect.html");
        response.sendRedirect("/basic/redirect.html");
    }

}