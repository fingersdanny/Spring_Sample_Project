package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // Transaction = commit 해야 db에 반영되는데 안하면 안돼
    // 이걸 해놓으면 테스트를 해도 데이터를 db에 반영을 안하기 때문에 다른 테스트도 바로 진행 할 수 있어
    // 테스트에 있는 데이터를 원래 db에 반영안하고 테스트시에만 사용한다.
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    // test case는 필요한거 injection 필드주입으로 해도 된다.

//    @BeforeEach 직접 JVM에 올려서 테스트 할때 constructor를 생성해서 만듬
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    }
//    @AfterEach
//    public void afterEach() {
//        memberRepository.clearStore();
//    } 메모리 db에 있는 데이터를 다음 테스트를 위해 지우는 코드
    @Test
    // 테스트는 한글로 적어도 돼...
    // db에 연결한 이후에는 테스트 전용 db를 따로 구축함
    void 회원가입() {
        // 상황에 따라 점점 변경해가면서 할 수 있도록 습관을 들여놓자.
        // 다음과 같이 주석을 쓰고 테스트를 진행하면 편해
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //정상 플로우 말고 예외 플로우가 더 중요해요
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.123123");
//        }


        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}