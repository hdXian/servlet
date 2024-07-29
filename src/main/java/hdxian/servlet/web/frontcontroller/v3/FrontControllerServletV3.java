package hdxian.servlet.web.frontcontroller.v3;

import hdxian.servlet.web.frontcontroller.ModelView;
import hdxian.servlet.web.frontcontroller.MyView;
import hdxian.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hdxian.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hdxian.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        ControllerV3 controller = controllerMap.get(requestURI);

        if(controller == null) {
            System.out.println("controller is null");
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // generate paramMap from HttpServletRequest
        Map<String, String> paramMap = generateParamMap(request);

        // controller returns the result by modelView
        ModelView mv = controller.process(paramMap);

        // get logical view name from modelView
        String logicalViewName = mv.getLogicalViewName();

        // generate view from logical view name
        MyView view = viewResolver(logicalViewName);

        // render view by model
        Map<String, Object> model = mv.getModel();

        // wii dispatch to jsp
        view.render(model, request, response);

    }

    private static MyView viewResolver(String logicalViewName) {
        // generate physical view path
        String viewName = "/WEB-INF/views/" + logicalViewName + ".jsp";

        // create view
        return new MyView(viewName);
    }

    private static Map<String, String> generateParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
