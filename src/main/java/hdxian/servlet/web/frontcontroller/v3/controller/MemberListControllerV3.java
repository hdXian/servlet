package hdxian.servlet.web.frontcontroller.v3.controller;

import hdxian.servlet.domain.member.Member;
import hdxian.servlet.domain.member.MemberRepository;
import hdxian.servlet.web.frontcontroller.ModelView;
import hdxian.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private final MemberRepository repository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = repository.findAll();

        // put member list into model
        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members);
        return mv;
    }

}
