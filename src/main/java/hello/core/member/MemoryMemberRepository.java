package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component // memoryMemberRepository
public class MemoryMemberRepository implements MemberRepository {
    // 실제로는 HashMap 말고 동시성 문제로 ConcurrentHashMap 을 사용해야 한다.
    // 자세한 내용은 따로 공부 할 것
    private  static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
