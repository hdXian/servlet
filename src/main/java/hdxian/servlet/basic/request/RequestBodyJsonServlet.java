package hdxian.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hdxian.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "RequestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    // powered by Jackson (Spring Boot default included library)
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messageBody = " + messageBody);

        // read JSON format string and generate an Object
        // ignore upper/lower case of class properties? idk
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

        System.out.println("username = " + helloData.getUsername());
        System.out.println("age = " + helloData.getAge());

        response.getWriter().write("request successfully accepted. check the server console.");

    }

}
