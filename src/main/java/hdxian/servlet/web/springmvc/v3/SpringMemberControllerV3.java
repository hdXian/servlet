package hdxian.servlet.web.springmvc.v3;

import hdxian.servlet.domain.member.Member;
import hdxian.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private final MemberRepository repository = MemberRepository.getInstance();

    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    public String newForm() {
        return "new-form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam("username") String username,
                       @RequestParam("age") int age,
                       Model model) {

        // generate and save new member
        Member member = new Member(username, age);
        repository.save(member);

        model.addAttribute("member", member);
        return "save";
    }

    // Mapped "/springmvc/v2/members" (class level default)
    @RequestMapping(method = RequestMethod.GET)
    public String members(Model model) {
        List<Member> members = repository.findAll();

        model.addAttribute("members", members);
        return "members";
    }

}
