#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy()
{
  IDLE_PORT=$(find_idle_port)

  echo "> 전환할 port : IDLE_PORT"
  echo "> Port 전환"
  echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service_url.inc
  #하나의 문장으로 만들어 파이프라인(|)으로 넘겨줌, Nginx가 변경할 프록시 주소를 생성

  echo "> Nginx reload 시도"
  sudo service nginx reload #Nginx 설정을 다시 불러옴
}