package com.youngjun.book.springboot.web.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Posts, Long> { //JpaRepository<Entity class, Pk type>
    //@Repository 를 추가할 필요도 없으며, CRUD 메소드가 자동적으로 추가된다.
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC") //한줄로 작성할 것
    List<Posts> findAllDesc();
}//PostRepository DAO (DB 레이어 접근자)
