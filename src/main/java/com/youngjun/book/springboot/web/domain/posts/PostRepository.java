package com.youngjun.book.springboot.web.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> { //JpaRepository<Entity class, Pk type>
    //@Repository 를 추가할 필요도 없으며, CRUD 메소드가 자동적으로 추가된다.

}//PostRepository DAO (DB 레이어 접근자)
