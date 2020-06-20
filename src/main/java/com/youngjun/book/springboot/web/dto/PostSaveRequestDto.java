package com.youngjun.book.springboot.web.dto;

import com.youngjun.book.springboot.web.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostSaveRequestDto {
    private String title;
    private String content;
    private String author;
    private Integer bgNum;

    @Builder
    public PostSaveRequestDto(String title, String content, String author, Integer bgNum){
        this.title = title;
        this.content = content;
        this.author = author;
        this.bgNum = bgNum;
    }//PostSaveRequestDto

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .bgNum(bgNum).build();
    }//toEntity
}//PostSaveRequestDto
