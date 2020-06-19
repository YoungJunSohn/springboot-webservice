package com.youngjun.book.springboot.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileController {
    private final Environment env;

    @GetMapping("/profile")
    public String profile(){
        //현재 실행중인 ActiveProfile 을 모두 가져온다
        //real, oauth, real-db 등 설정 파일들이 모두 활성화되어 있다면 acvice에 3개가 모두 담겨있다.
        List<String> profiles = Arrays.asList(env.getActiveProfiles());

        List<String> realProfiles = Arrays.asList("real", "real1", "real2");
        String defaultProfile = profiles.isEmpty() ? "default" : profiles.get(0);

        return profiles.stream()
                .filter(realProfiles::contains)
                .findAny()
                .orElse(defaultProfile);
    }//profile
}//ProfileController

// 배포시 Nginx 에서 8081port를 사용할 것인지 , 8082port를 사용할 것인지 판단하는 API