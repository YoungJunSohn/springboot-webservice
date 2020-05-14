package com.youngjun.book.springboot.web.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass//JPA Entity 클래스들이 BaseTimeEntity 를 상속할 경우 필드들(createdDate, modifiedDate) 도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity { //상속시키기 위한 추상클래스 작성

    @CreatedDate
    private LocalDateTime createdTime;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}//BaseTimeEntity
//모든 Entity의 상위 클래스가 되어, Entity 들의 cratedDate, modifiedDate 를 자동으로 관리하는 추상클래스