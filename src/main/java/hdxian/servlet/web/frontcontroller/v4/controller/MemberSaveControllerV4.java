package hdxian.servlet.web.frontcontroller.v4.controller;

import hdxian.servlet.domain.member.Member;
import hdxian.servlet.domain.member.MemberRepository;
import hdxian.servlet.web.frontcontroller.v4.ControllerV4;

import java.util.Map;

public class MemberSaveControllerV4 implements ControllerV4 {

    private final MemberRepository repository = MemberRepository.getInstance();

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {

        // get query parameter from paramMap
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        // create and save new member
        Member member = new Member(username, age);
        repository.save(member);

        // save member into model
        model.put("member", member);

        return "save";
    }

}
