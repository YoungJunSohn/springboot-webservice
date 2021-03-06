#!/usr/bin/env bash

#health.sh : start.sh가 제대로 실행되었는가 체크
ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh
source ${ABSDIR}/switch.sh

IDLE_PORT=$(find_idle_port) #비어있는 8081 또는 8082 포트 리턴받음

echo "> Health Check Start!"
echo "> IDLE_PORT : $IDLE_PORT 번 포트가 쉬고 있습니다."
echo "> curl -s http://localhost:$IDLE_PORT/profile : 쉬는 포트 체크 시작"
sleep 10

for RETRY_COUNT in {1..5}
do
  RESPONSE=$(curl -s http://localhost:${IDLE_PORT}/profile)
  UP_COUNT=$(echo ${RESPONSE} | grep 'real' | wc -l) #grep(문자열 판별) -> "real" 문자가 있는경우 글자 수

  echo ">>>>>>>>> RESPONSE -> $RESPONSE" #찍어보자 RESPONSE ?
  echo ">>>>>>>>> UP_COUNT -> $UP_COUNT"

  if [ ${UP_COUNT} -ge 1 ]
  then # $up_count >=1 ("real" 문자열이 있는 경우 UP_COUNT가 1보다 크게 됨)
    echo "> Health check 성공!"
    switch_proxy #Nginx와 연결되지 않은 포트로 스프링부트가 잘 수행된 경우, proxy 설정을 변경
    break
  else
    echo "> Health check의 응답을 알 수 없거나 혹은 실행 상태가 아닙니다."
    echo "> Health check : ${RESPONSE}"
  fi

  if [ ${RETRY_COUNT} -eq 5 ]
  then
    echo "> Health check 실패."
    echo "> Nginx에 연결하지 않고 배포를 종료합니다."
    exit 1
  fi

  echo "> Health check 연결 실패. 재시도..."
  sleep 10
done
