package com.youngjun.book.springboot.web;

import com.youngjun.book.springboot.web.service.posts.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


}//IndexController
