package hdxian.servlet.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // singleton
    private static final MemberRepository instance = new MemberRepository();

    // private Constructor
    private MemberRepository() {
    }

    public static MemberRepository getInstance() {
        return instance;
    }

    // save Member
    // auto generate id by sequence
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // return Member by id
    public Member findById(Long id) {
        return store.get(id);
    }

    // return ArrayList of Members
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
