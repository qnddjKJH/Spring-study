package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
    // ...test 는 한글로 메소드가 작성이 가능하다!  엌ㅋ
    // given, when, then 문법 추천
    // 테스트가 커질 경우 매우 복잡해 질 수 있으므로 이정표라고 생각하면 쉽다.

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach // 테스트가 동작하기 전에 실행하는 메소드 (매번)
    public void beforeEach() {
        // DI 의존 객체 주입 - 생성자 방식
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach // 테스트 동작 후 실행 (매번)
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("hello");
        
        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        // assertThrows 는 junit.jupiter.api 안에 있다
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


/*// try catch 로 예외를 잡을 수도 있긴 하다.
try {
    memberService.join(member2);
    fail();
} catch (IllegalStateException e) {
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
}*/
        
        
        //then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }

}