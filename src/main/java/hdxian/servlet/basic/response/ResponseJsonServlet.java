package hdxian.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hdxian.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Content-Type: application/json
        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8"); // application/json doesn't need a charset.

        HelloData helloData = new HelloData();
        helloData.setUsername("유미미");
        helloData.setAge(19);

        // {"username": "gdh", "age": 19}
        String string = objectMapper.writeValueAsString(helloData);

        // response.getWriter() adds default charset automatically.
        // json standard charset is utf-8, but writer can add order charset.
//        response.getWriter().write(string);

        // so using getOutputStream() is recommended.
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(string.getBytes(StandardCharsets.UTF_8));

    }
}
