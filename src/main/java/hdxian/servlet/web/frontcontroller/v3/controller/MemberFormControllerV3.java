package hdxian.servlet.web.frontcontroller.v3.controller;

import hdxian.servlet.web.frontcontroller.ModelView;
import hdxian.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        // just return modelView named "new-form"
        return new ModelView("new-form");
    }
}
