package com.youngjun.book.springboot.web.dto;

import com.youngjun.book.springboot.web.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.content = entity.getContent();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();

    }//PostsResponseDto

    //DTO는 Entity 의 필드 중 일부만 사용하므로 생성자에 Entity를 받아 필드에 값을 세팅한다.
}//PostsResponseDto
