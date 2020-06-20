package com.youngjun.book.springboot.config.auth.dto;

import com.youngjun.book.springboot.web.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser (User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }//SessionUser(User)
}//SessionUser

//인증된 사용자 정보만을 필요로 하는 클래스
/*
* User 클래스를 사용하지 않는 이유
* User 클래스는 Entity 로 지정되어 있기 때문인데,
* 엔티티 클래스는 언제 다른 엔티티와 관계가 형성될 지 모른다.
* 직렬화 기능을 User 에 추가했을 경우, 추후에 User 엔티티가 다른 자식 엔티티를 가질 때
* 직렬화 대상에 자식들까지 포함이 되어 성능 이슈 또는 간섭 효과가 발생할 확률이 높다
* 따라서 직렬화 기능을 가진 세션 Dto를 하나 생성하였다.
* */

