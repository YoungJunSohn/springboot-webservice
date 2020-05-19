package com.youngjun.book.springboot.web.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUEST("ROLE_GUEST", "임시 계정"),
    USER("ROLE_USER","사용자 계정");

    private final String key;
    private final String title;

}//Role

//Enum이란? 열거형 (enumerated type) 이라 불리며, 서로 연관된 '상수'들의 집합이다. 여기서는 GUEST 와 USER
//스프링 시큐리티에서는 권한 코드에 항상 ROLE_ 이 앞에 붙어있어야만 한다. 그래서 코드 키값을 이렇게 지정했다.