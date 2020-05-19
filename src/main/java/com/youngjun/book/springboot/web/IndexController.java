package com.youngjun.book.springboot.web;

import com.youngjun.book.springboot.config.auth.dto.SessionUser;
import com.youngjun.book.springboot.web.dto.PostsResponseDto;
import com.youngjun.book.springboot.web.service.posts.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;
    private final HttpSession httpSession;


    @GetMapping("/")
    public String index(Model model){

        model.addAttribute("posts", postService.findAllDesc()); //게시글 출력

        SessionUser user = (SessionUser) httpSession.getAttribute("user"); //유저정보 가져오기

        if(user != null){
            // 로그인성공시 userName 속성에 유저 이름을 저장 후 모델에 첨부
            model.addAttribute("userName", user.getName());
        }//if

        return "index"; //index mustache 페이지로 보냄
    }//index()

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }//postsSave

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postService.findById(id); //id로
        model.addAttribute("post", dto);
        return "posts-update";
    }//postsUpdate


}//IndexController
