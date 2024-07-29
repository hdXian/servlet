package hdxian.servlet.web.frontcontroller.v4;

import hdxian.servlet.web.frontcontroller.MyView;
import hdxian.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hdxian.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hdxian.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMap.get(requestURI);

        if(controller == null) {
            System.out.println("controller is null");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // generate paramMap from HttpServletRequest
        Map<String, String> paramMap = generateParamMap(request);

        // create empty model
        Map<String, Object> model = new HashMap<>();

        // controller returns viewName by String
        String logicalViewName = controller.process(paramMap, model);

        // generate view from logical view name
        MyView view = viewResolver(logicalViewName);

        // wii dispatch to jsp
        view.render(model, request, response);

    }

    private static MyView viewResolver(String logicalViewName) {
        // generate physical view path
        String viewName = "/WEB-INF/views/" + logicalViewName + ".jsp";

        // create view
        return new MyView(viewName);
    }

    // put query parameters into hashMap
    private static Map<String, String> generateParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
