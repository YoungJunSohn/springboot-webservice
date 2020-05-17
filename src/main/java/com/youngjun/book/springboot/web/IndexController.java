package com.youngjun.book.springboot.web;

import com.youngjun.book.springboot.web.dto.PostsResponseDto;
import com.youngjun.book.springboot.web.service.posts.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostService postService;


    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postService.findAllDesc());
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
