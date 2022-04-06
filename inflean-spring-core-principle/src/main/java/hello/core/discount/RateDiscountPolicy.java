package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy
// @Qualifier("mainDiscountPolicy") 에서는 오타가 있어도 실행이 된다.
// 하지만 Annotation 을 만들어서 @MainDiscountPolicy 를 쓰면 오타가 날 때 에러 표시를 해주어서
// 편리한 장점이 있다.
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if( member.getGrade() == Grade.VIP ) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
