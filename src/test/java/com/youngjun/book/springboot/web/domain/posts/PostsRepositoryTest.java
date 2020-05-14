package com.youngjun.book.springboot.web.domain.posts;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @After
    public void cleanup(){
        postRepository.deleteAll();
    }//cleanup

    @Test
    public void baseTimeEntityTest(){

        //given 테스트 전
        LocalDateTime now = LocalDateTime.of(2020,5,14,23,38,50);
        postRepository.save(Posts.builder()
                .title("엔티티테스트제목1")
                .content("엔티티테스트내용1")
                .author("엔티티테스트글쓴이1")
                .build());

        //when 테스트 행위 및 대상
        List<Posts> postsList = postRepository.findAll();

        //then 테스트 및 검증
        Posts posts = postsList.get(0);

        System.out.println("만들었느뇨? -"+posts.getCreatedTime()+", 수정했느뇨?" +posts.getModifiedDate());

        assertThat(posts.getCreatedTime()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);


    }//baseTimeEntityTest

    @Test
    public void postingLoadTest(){
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("sohnyj37@gmail.com")
                .build());

        List<Posts> postsList = postRepository.findAll();
 
        Posts posts = postsList.get(0); //첫번째 글을 가져와 초기화한다.
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }//postingLoadTest
}//PostsRepositoryTest
