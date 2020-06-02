#!/usr/bin/env bash

#실행되지 않은 profile 찾기 : real1이 사용중인 경우 , real2가 실행되지 않고 있음
function find_idle_profile()
{
    RESPONSE_CODE=$curl -s -o /devnull -w "%{http_code}" http://localhost/profile)

    if [ ${RESPONSE_CODE} -ge 400 ] #400보다 큰 숫자의 에러 검출 (40x, 50x 모두 포함)
    then
      CURRENT_PROFILE=real2
    else
      CURRENT_PROFILE=$(curl -s http://localhost/profile)
    fi

    if [ ${CURRENT_PROFILE} == real1 ]
    then
      IDLE_PROFILE=real2
    else
      IDLE_PROFILE=real1
    fi

    echo "실행되지 않은 profile : ${IDLE_PROFILE}"
}

#실행되지 않은 profile의 port 찾기
function find_idle_port()
{
    IDLE_PROFILE=$(find_idle_profile)

    if  [ ${IDLE_PROFILE} == real1 ]
    then
      echo "현재 프로젝트를 실행하고 있지 않은 port : 8081"
    else
      echo "현재 프로젝트를 실행하고 있지 않은 port : 8082"
    fi
}
#bash 는 값은 리턴하는 기능이 없다. 따라서 제일 마지막 줄에 echo로 결과를 출력한 후,
#클라이언트에서 그 값은 잡아 사용한다. return 키워드와 마찬가지로 echo를 function중간에 사용해서는 안된다.