package com.youngjun.book.springboot.web;

import com.youngjun.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class) //스프링부트와 Junit 사이에 연결자 역할
@WebMvcTest(controllers = HelloController.class,
                excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE
                        , classes = SecurityConfig.class)
                })
public class HelloControllerTest {

    @Autowired //빈 와이어링
    private MockMvc mvc; //mock 객체 생성

    @Test
    @WithMockUser(roles="USER")
    public void helloDTOTest() throws Exception{
        String name = "테스트입니다";
        int amount = 2222;


        mvc.perform(get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

        /*
        jsonPath : json 응답값을 필드별로 검증하기 위하여 사용하였다.
        $ 선택자를 기준으로 필드명을 명시한다.
        * */

    }//helloDTOTest()

    @Test
    @WithMockUser(roles="USER")
    public void helloTest() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
        
    };//helloTest()
}//HelloControllerTest
