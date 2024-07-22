package hdxian.servlet.web.servlet;

import hdxian.servlet.domain.Member;
import hdxian.servlet.domain.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

// response html page with success msg
@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/new")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository repository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get query parameters
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // save member logic
        Member member = new Member(username, age);
        System.out.println("member = " + member);
        repository.save(member);

        // generate response page
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        String respHtml = "<html>\n" +
                "<head>\n" +
                " <meta charset=\"UTF-8\">\n" +
                "</head>\n" +
                "<body>\n" +
                "성공\n" +
                "<ul>\n" +
                " <li>id="+member.getId()+"</li>\n" +
                " <li>username="+member.getUsername()+"</li>\n" +
                " <li>age="+member.getAge()+"</li>\n" +
                "</ul>\n" +
                "<a href=\"/index.html\">메인</a>\n" +
                "</body>\n" +
                "</html>";

        PrintWriter writer = response.getWriter();
        writer.write(respHtml);

    }

}
