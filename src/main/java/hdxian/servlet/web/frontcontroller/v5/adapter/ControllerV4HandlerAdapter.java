package hdxian.servlet.web.frontcontroller.v5.adapter;

import hdxian.servlet.web.frontcontroller.ModelView;
import hdxian.servlet.web.frontcontroller.MyHandlerAdapter;
import hdxian.servlet.web.frontcontroller.v4.ControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    // controller logic executed from here.
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        // casting
        ControllerV4 controller = (ControllerV4) handler;

        // create paramMap, model
        Map<String, String> paramMap = generateParamMap(request);
        Map<String, Object> model = new HashMap<>();

        // controller v4 just return string.
        String logicalViewName = controller.process(paramMap, model);

        // handler need to create new ModelView and set model.
        ModelView modelView = new ModelView(logicalViewName);
        modelView.setModel(model);

        return modelView;
    }

    // put query parameters into hashMap
    private static Map<String, String> generateParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
