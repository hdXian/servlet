package hdxian.servlet.web.frontcontroller.v3.controller;

import hdxian.servlet.domain.member.Member;
import hdxian.servlet.domain.member.MemberRepository;
import hdxian.servlet.web.frontcontroller.ModelView;
import hdxian.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private final MemberRepository repository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {

        // get query params from Map
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // generate and save new member
        Member member = new Member(username, age);
        repository.save(member);

        // put saved member into model
        ModelView mv = new ModelView("save");
        mv.getModel().put("member", member);
        return mv;
    }
}
