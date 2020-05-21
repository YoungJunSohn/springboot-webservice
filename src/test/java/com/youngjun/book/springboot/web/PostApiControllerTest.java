package com.youngjun.book.springboot.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.youngjun.book.springboot.web.domain.posts.PostRepository;
import com.youngjun.book.springboot.web.domain.posts.Posts;
import com.youngjun.book.springboot.web.dto.PostSaveRequestDto;
import com.youngjun.book.springboot.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //랜덤포트로 실행한다.
public class PostApiControllerTest {
    //@WebMVCTest 의 경우 JPA기능이 작동하지 않았기 때문에 @SpringBootTest를 사용
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }//setup

    @After
    public void tearDown() throws Exception{
        postRepository.deleteAll();
    }//tearDown




    @Test
    @WithMockUser(roles="USER")
    public void postsUpdate() throws Exception{
        //Given (테스트 전의 상태)
        Posts savedPosts = postRepository.save(Posts.builder()
                .title("제목1!")
                .content("내용쓰1!")
                .author("글쓴이1").build());

        Long updateId = savedPosts.getId();
        String expectedTitle = "title2"; //바꿀 제목
        String expectedContent = "content2"; // 바꿀 내용

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build(); //update 메소드를 사용, 글 정보 수정

        String url = "http://localhost:"+port+"/api/v1/posts/"+updateId; // 맵핑 url 주소

        //When (테스트 행위 및 대상)
        /*HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);
        ResponseEntity<Long> responseEntity =
                restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);*/

        mockMvc.perform(put(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper()
                .writeValueAsString(requestDto)))
                .andExpect(status().isOk());



        //then (테스트 및 검증)
        /*assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);*/
        List<Posts> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }//postsUpdate


    @Test
    @WithMockUser(roles="USER")
    public void PostsRegistration() throws Exception{
        //given
        String title = "제목입니다";
        String content = "내용입니다";

        PostSaveRequestDto requestDto = PostSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("작성자!").build();

        String url = "http://localhost:" + port + "/api/v1/posts";



        //when
        /*ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);



        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);*/


        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());



        //then
        List<Posts> all = postRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);

        //test 결과, http host = null 이라는 오류가 발생하는데 원인이 뭘까??
        // url 주소 http:// << // 를 누락해서 발생한 오류

    }//PostsRegistration







}//PostApiControllerTest

