package com.youngjun.book.springboot.web.service.posts;

import com.youngjun.book.springboot.web.domain.posts.PostRepository;
import com.youngjun.book.springboot.web.domain.posts.Posts;
import com.youngjun.book.springboot.web.dto.PostSaveRequestDto;
import com.youngjun.book.springboot.web.dto.PostsListResponseDto;
import com.youngjun.book.springboot.web.dto.PostsResponseDto;
import com.youngjun.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional //목록 조회
    public List<PostsListResponseDto> findAllDesc(){
        return postRepository.findAllDesc()
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }//findAllDesc

    @Transactional
    public Long save(PostSaveRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }//save


    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postRepository.findById(id)
                .orElseThrow( (  ) -> new IllegalArgumentException("해당 게시글이 없습니다 ! id = "+id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }//update

    @Transactional
    public void delete(Long id){
        Posts posts = postRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다! id="+id));

        postRepository.delete(posts);
    }//delete

    public PostsResponseDto findById(Long id){
        Posts entity = postRepository.findById(id)
                .orElseThrow( ( ) -> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));

        return new PostsResponseDto(entity);
    }//findById
}//PostService

/*update 기능에서 데이터베이스에 쿼리를 넘겨주는 부분이 없다?? 왜일까??
    JPA의 영속성 컨텍스트 때문
    영속성 컨텍스트란 Entity를 영구적으로 저장하는 환경이다.
    JPA는 Entity가 영속성 컨텍스트에 포함되는가와 아닌가에 따라 달라진다.
    트랙잭션 안에서 데이터베이스에 접근하여 데이터를 가져오면 이 경우, 영속성 컨텍스트가 유지된 것!
    영속성 컨텍스트가 유지된 상태에서 데이터 값을 변경하면 트랜잭션이 끝나는 시점에
    해당 테이블에 변경된 부분이 바로 반영된다. 즉 Entity 객체의 값만 변경하면
    별도의 Update 쿼리를 날릴 필요가 없는 것이다!  >>> Dirty Checking
* */
