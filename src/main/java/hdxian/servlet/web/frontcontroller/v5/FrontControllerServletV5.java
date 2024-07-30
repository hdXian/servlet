package hdxian.servlet.web.frontcontroller.v5;

import hdxian.servlet.web.frontcontroller.ModelView;
import hdxian.servlet.web.frontcontroller.MyHandlerAdapter;
import hdxian.servlet.web.frontcontroller.MyView;
import hdxian.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hdxian.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hdxian.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hdxian.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    // now, handler is not just controller. any Object can be mapped.
    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // get handler from handlerMappingMap (based on requestURI)
        Object handler = getHandler(request);

        // there are no matching URI among handlers(controllers)
        if(handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // search supported adapter
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        // adapter will call Controller in handle(), returns ModelView
        ModelView modelView = adapter.handle(request, response, handler);

        // get view name, model from ModelView
        String logicalViewName = modelView.getLogicalViewName();
        Map<String, Object> model = modelView.getModel();

        // generate MyView with "/WEB-INF/views/" + viewName + ".jsp"
        MyView view = viewResolver(logicalViewName);

        // will dispatch jsp
        view.render(model, request, response);
    }

    private static MyView viewResolver(String logicalViewName) {
        // generate physical view path
        String viewName = "/WEB-INF/views/" + logicalViewName + ".jsp";

        // create view
        return new MyView(viewName);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {

        // search adapter
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if(adapter.supports(handler)) { // check by instanceof
                return adapter;
            }
        }

        // if there are no supported adapter, throw Illegal Args Exception
        throw new IllegalArgumentException("no supported Handler Adapter.(Handler: " + handler + ")");
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }


}
