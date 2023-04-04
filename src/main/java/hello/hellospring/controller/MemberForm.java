package hello.hellospring.controller;

public class MemberForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

// 서버를 끄면 저장소가 따로 연결되어 있지 않고 메모리로 구현했기 때문에 회원 데이터는 다 날라감