package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Component Scan을 하지 않고 스프링 빈에 직접 등록하는 방법
@Configuration
public class SpringConfig {

//    private DataSource dataSource;

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);

    }

    // 이렇게 해주면 Aop가 쓰이는줄 다른 사람들이 알 수 있음

    // Spring Bean에 등록된 memberRepository를 넣어서 기존의 Component Scan일때 처럼 연결 해줌
//    @Bean
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcMemberRepository(dataSource);
////        return new JpaMemberRepository(MemberRepository);
    }
