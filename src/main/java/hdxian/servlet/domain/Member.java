package hdxian.servlet.domain;

import lombok.Getter;
import lombok.Setter;

// Member domain
// has id, username, age property
@Getter @Setter
public class Member {

    private Long id;
    private String username;
    private int age;

    // default constructor
    public Member() {

    }

    // auto generated id
    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
