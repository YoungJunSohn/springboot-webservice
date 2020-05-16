package com.youngjun.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(){

        return "index"; //index mustache 페이지로 보냄
    }//index()

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }//postsSave

}//IndexController
