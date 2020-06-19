package com.youngjun.book.springboot.web.dto;

import com.youngjun.book.springboot.web.domain.posts.Posts;
import lombok.Getter;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String content;
    private String modifiedDate;
    private String bgNum;



    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.content = entity.getContent();

        //날짜 변환 (LocalDateTime > Date)
        this.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm:ss"));


        //랜덤카드 배경 이미지를 위한 이미지 숫자(1~10)
        if(entity.getBgNum()<10){
            this.bgNum = "0"+entity.getBgNum().toString();
        }else if(entity.getBgNum()==10){
            this.bgNum = ""+entity.getBgNum();
        }else if(entity.getBgNum().equals(null)){
            this.bgNum = ""+Math.floor(Math.random()*10+1)+"";
        }


    }//PostsListResponseDto()
}//PostsListResponseDto
