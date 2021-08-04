package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    // Optional Java 8 부터 제공되는 기능
    // 만일 Id가 없다면? Null 을 반환해야하는데 그런 문제를 해결하기 위해 등장
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
