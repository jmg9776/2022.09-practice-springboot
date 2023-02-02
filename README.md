# sandbox-server

Springboot를 공부하면서 제작한 프로젝트입니다!
구현을 private repo에서 구현을 했는데 db에 관련한 정보가 남아있는 관계로 새로운 repo에 부득이하게 새로 업로드하게 되어 깃로그가 남아있지 않습니다...

## 구현한 기능 목록
RabbitMq를 통한 큐잉
Redis를 통한 캐싱
SpringSecurity를 이용한 JWT 인증과 hMac인증
세션을 통한 로그인 로그아웃
bootstrap 무료 템플릿 적용
orm을 이용한 데이터베이스 연동(hibernate)
critical effect발생시 telegram으로 알림
feignClient를 이용한 http통신

#rabbitMq
docker run -d -p 15672:15672 -p 5672:5672 --name rabbitmq rabbitmq
docker exec rabbitmq rabbitmq-plugins enable rabbitmq_management
