package hdxian.servlet.domain.member;

import hdxian.servlet.domain.Member;
import hdxian.servlet.domain.MemberRepository;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

// test class doesn't ask for public access (since Junit 5)
class MemberRepositoryTest {

    MemberRepository repository = MemberRepository.getInstance();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        // check saved member is same to found member
        System.out.println("MemberRepositoryTest.save");
        // given
        Member member = new Member("hello", 20);

        // when
        Member savedMember = repository.save(member);

        // then
        Member findMember = repository.findById(savedMember.getId());
        Assertions.assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    public void findAll() {
        // check the number of all saved member is 2, and the store contains member1, member2
        System.out.println("MemberRepositoryTest.findAll");
        // given
        Member member1 = new Member("member1", 19);
        Member member2 = new Member("member2", 20);

        // when
        repository.save(member1);
        repository.save(member2);

        List<Member> result = repository.findAll();

        // then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(member1, member2);

    }

}
