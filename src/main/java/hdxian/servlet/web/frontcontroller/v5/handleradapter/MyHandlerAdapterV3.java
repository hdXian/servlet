package hdxian.servlet.web.frontcontroller.v5.handleradapter;

import hdxian.servlet.web.frontcontroller.ModelView;
import hdxian.servlet.web.frontcontroller.MyHandlerAdapter;
import hdxian.servlet.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyHandlerAdapterV3 implements MyHandlerAdapter {

    // check handler is compatible with ControllerV3
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    // Controller returns ModelView
    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        // casting to ControllerV3
        ControllerV3 controller = (ControllerV3) handler;

        // generate paramMap from HttpServletRequest
        Map<String, String> paramMap = generateParamMap(request);

        ModelView modelView = controller.process(paramMap);
        return modelView;
    }

    private static Map<String, String> generateParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}
