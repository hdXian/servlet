package hdxian.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/mvc-servlet/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // form jsp file path
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // dispatch to viewPath
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);

        // move to other servlet or jsp.
        // server calls to /WEB-INF/views/new-form.jsp internally
        dispatcher.forward(request, response);
    }

}
