package hdxian.servlet.web.frontcontroller.v3;

import hdxian.servlet.web.frontcontroller.ModelView;

import java.util.Map;

public interface ControllerV3 {
    // get params from Map<String, String> (v2 - HttpServletRequest)
    ModelView process(Map<String, String> paramMap);
}
