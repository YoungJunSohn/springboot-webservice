package com.youngjun.book.springboot.web.service.posts;

import com.youngjun.book.springboot.web.domain.posts.PostRepository;
import com.youngjun.book.springboot.web.dto.PostSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostSaveRequestDto requestDto){
        return postRepository.save(requestDto.toEntity()).getId();
    }//save
}//PostService
