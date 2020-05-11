package com.youngjun.book.springboot.domain.posts;


import com.youngjun.book.springboot.web.domain.posts.PostRepository;
import com.youngjun.book.springboot.web.domain.posts.Posts;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
