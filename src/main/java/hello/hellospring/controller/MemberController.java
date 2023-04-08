package hello.hellospring.controller;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
// 컨트롤러 표기가 있으면 이 관련된 동작을 스프링이 실행하고 이를
// 스프링 컨트롤러에서 스프링 빈이 관리된다고 한다
public class MemberController {

    private final MemberService memberService;
    // new 이렇게 하면 할때마다 새로운 객체가 생기기 때문에 안돼. 저장소라고 하면 새로운 저장소가 만들어지는것;;
    // 스프링 컨테이너에 등록하면 이러한 문제 해결할 수 있어

    /*
    Dependency Injection(DI)의 방법
     */
    //필드 주입: 별로 안좋음, 중간에 바꿔치기할 수 있는 방법이 전혀없음
    // @Autowired private MemberService memberService;

    //생성자 주입 : 요즘 추천되는 방식 (Application 생성 시점에서)
    @Autowired
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
        System.out.println("memberService =" + memberService.getClass());
    }

    //setter 주입
    // 생성자는 생성자대로 setter는 그 이후에 생성
    // @Autowired
    // public void setMemberService(MemberService memberService) {
    //  this.memberService = memberService;
    // }  계속 public으로 열려있어야 누군가 member controller 호출했을때 사용 가능
    // public으로 계속 열려있어야 해서 이후에 바꾸면 문제가 생김

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMembersForm";
    }

    // Data를 form 같은데 넣어서 데이터를 등록할때 @PostMapping 을 사용한다.
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model ){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
