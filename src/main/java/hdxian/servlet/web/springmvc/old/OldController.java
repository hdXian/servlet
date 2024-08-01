package hdxian.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// register bean by url
// HandlerMapping - BeanNameUrlHandlerMapping
// HandlerAdapter - SimpleControllerHandlerAdapter (ex. implements interface Controller)
@Component("/springmvc/old-controller") // if beanName.startsWith("/") -> mapping as handler...
public class OldController implements Controller {
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return new ModelAndView("new-form");
    }
}
