package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloSpringApplication {
// gradle은 의존적인 library들을 프로젝트 시작할 때 전부 설치하지 않아도 필요한 부분들을 가져온다
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
