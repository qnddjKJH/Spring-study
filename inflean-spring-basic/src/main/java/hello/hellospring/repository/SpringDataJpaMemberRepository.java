package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository 를 확장함과 동시에 Spring 이 관리한다.
// 자동으로 구현체를 구현해줌(내부 메소드 save, findById 등등...) 공통적으로 사용하는 것은
// 전부 공통화되어 제공된다
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);

}
