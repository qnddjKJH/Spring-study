package hello.core.member;

import hello.core.discount.DiscountPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // 컨트롤+쉬프트+엔터 하면 세미콜론 까지 뜬다. (원하는 객체가 등장하고 선택해야 한다)
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // MemberRepository Interface 만 의존하는 코드
    // 이제부터 의존 관계에 대한 고민은 '외부(AppConfig)' 에 맡기고 실행에만 집중 하면 된다.
    // DIP 완성, 관심사 분리
    private final MemberRepository memberRepository;

    // @Component 로 스프링 빈으로 등록은 하였다.
    // 그럼 의존 관계 주입은 어떻게 하나
    // ComponentScan 을 쓰게 되면 자동 등록이다 보니 수동으로 등록할 장소가 없다
    // 그렇기에 자동 등록(@Autowired)을 쓰게 되는 것이다.
    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
