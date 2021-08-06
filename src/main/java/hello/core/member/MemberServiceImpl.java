package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // 컨트롤+쉬프트+엔터 하면 세미콜론 까지 뜬다. (원하는 객체가 등장하고 선택해야 한다)
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
