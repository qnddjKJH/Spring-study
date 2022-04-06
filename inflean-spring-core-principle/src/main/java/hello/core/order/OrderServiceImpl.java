package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
// 여담 자바 기초 final 은 무조건 값이 들어가 있어야 한다.
// 직접적으로 들어가든 생성자를 통해 들어가든
// @RequiredArgsConstructor
// 필수 값을 가지고(final 지정) 생성자를 만들어주는 Lombok annotation 이다.
// final 이 붙은 필드를 모아서 생성자를 자동으로 만들어준다. 보이지 않아도 실제 호출이 가능하다.
public class OrderServiceImpl implements OrderService {

    //    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 위 방법은 DIP, OCP 위반이다... 어찌되었든 생성하는 코드가 바뀌어서
    // 클라이언트 코드가 변경되기 때문 해결방법은 밑에 코드 처럼 만든다.
    // 그리고 '누군가' 가 클라이언트인 OrderServiceImpl 에 DiscountPolicy 의 구현 객체를
    // 대신 생성하고 넣어 줘야한다.
    // 해당 방법으로 의존 객체를 넣어주면 DIP 를 철저히 지켜주게 된다.
    // 클라이언트 코드인 해당 클래스가 아닌 AppConfig 에서 넣어주는 객체만 바꿔주면 된다.
    // DIP 완성, 관심사 분리

        private final DiscountPolicy discountPolicy;
        private final MemberRepository memberRepository;


        @Autowired
        public OrderServiceImpl(@MainDiscountPolicy DiscountPolicy discountPolicy,
                                MemberRepository memberRepository) {
            this.discountPolicy = discountPolicy;
            this.memberRepository = memberRepository;
        }
    // @RequiredArgsConstructor 로 위의 생성자가 생성된다. final 은 필수값

    // 필드 주입 방식
    // 코드가 간결해져서 유혹이 심하지만 외부에서 변경이 불가능하고
    // 테스트하기 힘들다는 치명적인 단점이 있다.
    // DI 프레임워크가 없으면 아무것도 할 수 없다.
    // 결론 - 사용하지 말자.
    // 사용하는 곳 - 어플리케이션의 '실제 코드와 상관없는 테스트 코드'
    //             - 스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만 특별한 용도로 사용
//    @Autowired private DiscountPolicy discountPolicy;
//    @Autowired private MemberRepository memberRepository;
//
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
//
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
