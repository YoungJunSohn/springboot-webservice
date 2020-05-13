package com.youngjun.book.springboot.web.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // Java Persistence Api annotation 를 사용한다.
public class Posts { //실제 DB 테이블과 매칭될 클래스

    @Id //
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }//Posts



    //수정
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }//update
}//Posts
