package hdxian.servlet.web.springmvc.v2;

import hdxian.servlet.domain.member.Member;
import hdxian.servlet.domain.member.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SpringMemberControllerV2 {

    private final MemberRepository repository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v2/members/new-form")
    public ModelAndView newForm() {
        // just return modelView named "new-form"
        return new ModelAndView("new-form");
    }

    @RequestMapping("/springmvc/v2/members/save")
    public ModelAndView save(HttpServletRequest request) {
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

    @RequestMapping("/springmvc/v2/members")
    public ModelAndView members() {
        List<Member> members = repository.findAll();

        // put member list into model
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        return mv;
    }


}
