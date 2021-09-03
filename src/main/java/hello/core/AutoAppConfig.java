package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// ComponentScan 은 @Component 가 붙은 것을 전부 스캔하여 스프링 빈으로 등록한다.
// 지금은 동일한 역할의 파일인 AppConfig 파일 및 각종 @Configuration 이 붙은 파일들을 제외해준다.
// 기존 예제를 최대한 남기기 위해서 하는 것이고 원래라면 AppConfig 파일은 제거하거나 한다.
// 왜 제외하는가 하면 @Configuration 클래스에 들어가면 안에 @Component 가 붙어있는 것을 확인 할 수 있다.
@Configuration
@ComponentScan(
        basePackages = "hello.core",
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    } // 현재 MemoryMemberRepository 는 자동 등록되어 있는데
    // 이렇게 '스프링' 에서는 수동 등록 하면 자동 등록 빈을 오버라이딩 해버린다.
    // 하지만 현실은 의도적으로 설정해서 이런 결과가 나온다기 보다는 꼬여서 만들어지는 경우가 대부분
    // 그래서 최근 '스프링 부트' 에서는 수동과 자동 빈 등록이 충돌이 나면 오류가 발생하도록 기본 값을 바꿈


}

// 권장 방법
// 설정 정보 클래스의 위치를 프로젝트 최상단에 두는 방법
// 컴포넌트 스캔 기본 대상
// @Component 뿐만 아니라 다음과 같은 내용도 추가 대상에 포함된다.
// @Controller, @Repository, @Service, @Configuration
// 특이하게 @Service 는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기에 있겠구나
// 하고 비즈니스 계층을 인식하는데 도움이 된다.

/*
* Component list
* MemberServiceImpl
* MemoryMemberRepository
* RateDiscountPolicy
* */