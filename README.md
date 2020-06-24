
<a href="http://ec2-13-209-238-146.ap-northeast-2.compute.amazonaws.com:8080">
<img src="/src/main/resources/static/images/app/logo-github.png">
</a>

> SpringBoot와 AmazonWebService를 이용하여 웹 서비스 구축


<br/>
<br/>
<br/>

---
> 개발 환경 및 인프라

<img src="/src/main/resources/static/images/git/develop-infra.png">

* IDE : IntelliJ Ultimate
* Build tool : Gradle 4
* Language : java 8

* RDBMS : h2, MySQL

* Framework : Springboot2
* Library : lombok, hibernate(JPA 구현체 라이브러리), JUnit5

* Template Engine : Mustache

<br/>
<br/>
<br/>

---
> 이용 기술

* JPA
  * Spring Data JPA(관계형 데이터베이스 정의 모듈)

* Spring Security
  * OAuth 2.0 / Google, Naver(개발 중)

* AWS
  * EC2 (가상 인스턴스 생성 및 서버화) 
  * RDS (데이터베이스 설치/운영)
  * S3 (Codedeploy 배포 파일 관리)
 
* Travis CI (지속적 배포)

* Nginx (무중단 배포 - **개발 예정**)

<br/>
<br/>
<br/>

---

> **현재 구현된 기능**

<br/>

* 게시판 등록/수정/조회/삭제 API 구현

* JPA Auditing으로 생성일 및 수정일 데이터 수집 자동화

* Spring Security 로 유저의 게시글 접근 권한을 제어

* OAuth2.0 으로 구글과 네이버 로그인 연동

* Nginx를 이용한 무중단 배포 (개발 중)


<br/><br/><br/>

---
>   많은 서비스업체에서 이용하고 있는 인프라를 직접 구축해보기 위해 시작하였습니다.<br/>
>
>   실제 서비스 환경에서 사용되는 AWS, CI(Continuous Integration) 등을 직접 구현해보고 구조가 어떠한지 경험할 수 있었습니다.


