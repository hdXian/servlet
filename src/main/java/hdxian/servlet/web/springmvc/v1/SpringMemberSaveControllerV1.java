package hdxian.servlet.web.springmvc.v1;

import hdxian.servlet.domain.member.Member;
import hdxian.servlet.domain.member.MemberRepository;
import hdxian.servlet.web.frontcontroller.ModelView;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class SpringMemberSaveControllerV1 {

    private final MemberRepository repository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request) {

        // get query params from Map
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        // generate and save new member
        Member member = new Member(username, age);
        repository.save(member);

        // put saved member into model
        ModelAndView mv = new ModelAndView("save");
        mv.addObject("member", member);
        // same as mv.getModel().put("member", member);
        return mv;
    }
}
