package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Component Scan을 하지 않고 스프링 빈에 직접 등록하는 방법
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    // Spring Bean에 등록된 memberRepository를 넣어서 기존의 Component Scan일때 처럼 연결 해줌
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }


}
