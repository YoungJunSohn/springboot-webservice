package com.youngjun.book.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor//선언된 모든 final필드가 포함된 생성자만을 생성해준다.
public class HelloResponseDTO {
        private final String name;
        private final int amount;
}//HelloResponseDTO

/*
* DTO는 Data-TransPort-Object 로 Getter와 생성자가 필요하다
* */
