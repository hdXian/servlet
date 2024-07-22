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
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private MemberRepository repository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        List<Member> members = repository.findAll();

        StringBuilder respHtml = new StringBuilder("<html>" +
                "<head>" +
                " <meta charset=\"UTF-8\">" +
                " <title>Title</title>" +
                "</head>" +
                "<body>" +
                "<a href=\"/index.html\">메인</a>" +
                "<table>" +
                " <thead>" +
                " <th>id</th>" +
                " <th>username</th>" +
                " <th>age</th>" +
                " </thead>" +
                " <tbody>");

        for (Member member: members) {
            respHtml.append(" <tr>")
                    .append(" <td>")
                    .append(member.getId())
                    .append("</td>")
                    .append(" <td>")
                    .append(member.getUsername())
                    .append("</td>")
                    .append(" <td>")
                    .append(member.getAge())
                    .append("</td>")
                    .append(" </tr>");
        }

        respHtml.append(" </tbody>")
                .append("</table>")
                .append("</body>")
                .append("</html>");

//        System.out.println("respHtml = " + respHtml.toString());

        PrintWriter writer = response.getWriter();
        writer.write(respHtml.toString());

    }

}
