package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

// 여담 자바 기초 final 은 무조건 값이 들어가 있어야 한다.
// 직접적으로 들어가든 생성자를 통해 들어가든
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

    public OrderServiceImpl(DiscountPolicy discountPolicy, MemberRepository memberRepository) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
