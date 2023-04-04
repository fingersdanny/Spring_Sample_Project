package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
// 컨트롤러 표기가 있으면 이 관련된 동작을 스프링이 실행하고 이를
// 스프링 컨트롤러에서 스프링 빈이 관리된다고 한다
public class MemberController {

    private final MemberService memberService;
    // new 이렇게 하면 할때마다 새로운 객체가 생기기 때문에 안돼. 저장소라고 하면 새로운 저장소가 만들어지는것;;
    // 스프링 컨테이너에 등록하면 이러한 문제 해결할 수 있어

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
