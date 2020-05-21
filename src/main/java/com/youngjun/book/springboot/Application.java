package com.youngjun.book.springboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static org.springframework.boot.SpringApplication.*;

//@EnableJpaAuditing //2. JPA Auditing 활성화 >> 테스트 코딩
@SpringBootApplication
public class Application {
    public static void main(String[] args){
        run(Application.class, args);
        
    }//main // 1. 프로젝트의 메인클래스 생성 완료
}//Application

//3. @EnableJpaAuditing 과 Application 클래스의 분리 > config 패키지에 JpaConfig생성