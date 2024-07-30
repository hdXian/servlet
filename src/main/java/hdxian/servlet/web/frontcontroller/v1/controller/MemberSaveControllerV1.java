package hdxian.servlet.web.frontcontroller.v1.controller;

import hdxian.servlet.domain.member.Member;
import hdxian.servlet.domain.member.MemberRepository;
import hdxian.servlet.web.frontcontroller.v1.ControllerV1;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    private MemberRepository repository = MemberRepository.getInstance();

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        repository.save(member);

        // store member data on request attribute
        // use HttpServletRequest as a model
        request.setAttribute("member", member);

        String viewPath = "/WEB-INF/views/save.jsp";

        // dispatch to viewPath
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

}
