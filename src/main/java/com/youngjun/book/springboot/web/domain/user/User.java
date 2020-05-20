package com.youngjun.book.springboot.web.domain.user;

import com.youngjun.book.springboot.web.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)//JPA 로 데이터베이스에 저장할 때 Enum 값을 어떤 형태로 저장할 지 결정(기본값 int)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }//User(String, String, String, Role)


    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;
        return this;
    }//update()

    public String getRoleKey(){
        return this.role.getKey();
    }//getRoleKey
}//User
