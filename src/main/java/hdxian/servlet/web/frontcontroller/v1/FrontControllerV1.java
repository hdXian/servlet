package hdxian.servlet.web.frontcontroller.v1;

import hdxian.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hdxian.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hdxian.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    // put each controller in controllerMap
    public FrontControllerV1() {
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontControllerV1.service");

        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

//        ControllerV1 controller = controllerMap.get(requestURI);
//        controller.process(request, response);

    }
}
