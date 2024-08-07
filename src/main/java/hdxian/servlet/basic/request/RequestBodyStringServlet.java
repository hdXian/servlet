package hdxian.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get message body into bytecode
        ServletInputStream inputStream = request.getInputStream();

        // byteCode -> character utils. must provide charset.
        String bodyString = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("bodyString = " + bodyString);
        response.getWriter().write("request successfully accepted. check the server console.");

    }
}
