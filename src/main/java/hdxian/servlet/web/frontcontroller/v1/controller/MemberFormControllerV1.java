package hdxian.servlet.web.frontcontroller.v1.controller;

import hdxian.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // form jsp file path
        String viewPath = "/WEB-INF/views/new-form.jsp";

        // dispatch to viewPath
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);

        // move to other servlet or jsp.
        // server calls to /WEB-INF/views/new-form.jsp internally
        dispatcher.forward(request, response);
    }
}
