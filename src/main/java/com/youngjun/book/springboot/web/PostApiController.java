package com.youngjun.book.springboot.web;

import com.youngjun.book.springboot.web.dto.PostSaveRequestDto;
import com.youngjun.book.springboot.web.dto.PostsResponseDto;
import com.youngjun.book.springboot.web.dto.PostsUpdateRequestDto;
import com.youngjun.book.springboot.web.service.posts.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostApiController {
    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostSaveRequestDto requestDto){
        return postService.save(requestDto);
    }//save

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id ,
                       @RequestBody PostsUpdateRequestDto requestDto){
        return postService.update(id, requestDto);
    }//update

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postService.findById(id);
    }//findById
}//PostApiController
