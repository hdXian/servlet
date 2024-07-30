package hdxian.servlet.web.frontcontroller.v2;

import hdxian.servlet.web.frontcontroller.MyView;
import hdxian.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hdxian.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hdxian.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    // put each controller in controllerMap
    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();
        System.out.println("requestURI = " + requestURI);

        ControllerV2 controller = controllerMap.get(requestURI);

        if(controller != null) {
            MyView myView = controller.process(request, response);
            myView.render(request, response);
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

    }
}
