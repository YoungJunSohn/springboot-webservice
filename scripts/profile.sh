#!/usr/bin/env bash
#profile.sh : 스크립트를 실행시켜줄 프로그램의 경로 지정

#실행되지 않은 profile 찾기 : real1이 사용중인 경우 , real2가 실행되지 않고 있음
function find_idle_profile()
{
    #현재 Nginx가 바라보고 있는 스프링부트가 정상적으로 수행되는가 확인
    #응답값을 HttpStatus (정상 : 200, 오류 : 400~503) 로 받음
    RESPONSE_CODE=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/profile)

    if [ ${RESPONSE_CODE} -ge 400 ] #-ge(>=) 400보다 큰 숫자의 에러 검출 (40x, 50x 모두 포함)
    then
      #real2 : 에러가 난다 -> 스프링부트가 실행되지 않고 있다. -> 비어있는 포트이다!
      CURRENT_PROFILE=real2
    else
      #에러가 나지 않는다 -> curl -s(silent) 부가적인 설명없이/ 해당 주소로 get방식 요청 시도
      CURRENT_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1 ] #CURRENT_PROFILE : 현재 스프링부트가 실행 중인 profile
    then
      IDLE_PROFILE=real2 #CURRNET_PROFILE==real1 -> real1 이 사용중이며, real2는 비어 있다.
    else
      IDLE_PROFILE=real1 #CURRENT_PROFILE!=real1 -> real2 가 사용중이며, real1이 비어 있다.
    fi

    echo "${IDLE_PROFILE}" #IDLE_PROFILE : Nginx와 연결되지 않은(비어있는) profile, 이를 받아 스프링부트를 실행할 것
}

#실행되지 않은 profile의 port 찾기
function find_idle_port()
{
    IDLE_PROFILE=$(find_idle_profile) #Nginx와 연결되지 않은(비어있는) profile을 IDLE_PROFILE에 선언

    if  [ ${IDLE_PROFILE} == real1 ] #real1과 비어있는 profile이 같은 경우, real1로 스프링 부트를 실행할 수 있다.
    then
      echo "8081" #real1(8081) 포트로 실행하기 위해 8081 숫자를 echo로 출력
    else
      echo "8082" #real2(8082) "
    fi
}
#bash 는 값은 리턴하는 기능이 없다. 따라서 제일 마지막 줄에 echo로 결과를 출력한 후,
#클라이언트에서 그 값을 잡아 사용한다. return 키워드와 마찬가지로 echo를 function중간에 사용해서는 안된다.