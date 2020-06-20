package com.youngjun.book.springboot.web.domain.user;


import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);//User 엔티티 관련
}//UserRepository
