package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

// static 으로 임포트가 추가되었다.
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 위에서 repository 를 공용으로 만들었기 때문에 테스트 순서와 상관없이
    // 먼저 들어간 데이터값이 저장되어 있어 테스트에서 충돌이 일어난다.
    // 그래서 한 테스트가 끝날 때마다 값을 초기화 해줄 필요가 있다.
    // AfterEach 는 매 메소드 실행이 끝날 때마다 실행된다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }


    @Test
    public void save() {
        Member member = new Member();
        member.setName("Spring");
        repository.save(member);

        // 반환 타입이 Optional 이면 Optional 에서 값을 꺼낼려면 .get()으로 가져올 수 있다.
        // 실무에서는 값을 바로 꺼내면 안됨
        Member result = repository.findById(member.getId()).get();

        // Assertions.assertThat(member).isEqualTo(result);
        // 위에서 Assertions 를 선택하고
        // alt+enter 후 static add 옵션을 하면 바로 assertThat 문법 사용가능하다.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        Member result = repository.findByName("Spring2").get();

        assertThat(result).isNotEqualTo(member1);
        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }


}