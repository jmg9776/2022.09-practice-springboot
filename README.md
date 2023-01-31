# sandbox-server
test
#rabbitMq
docker run -d -p 15672:15672 -p 5672:5672 --name rabbitmq rabbitmq
docker exec rabbitmq rabbitmq-plugins enable rabbitmq_management
