DOCKER IMAGE BUILD 

docker build -t docker-spring-boot .

DOCKER RUN IMAGE

docker run -p 8085:8085 docker-spring-boot

DOCKER TAG IMAGE

docker tag 73692afd539b rajeshg87/spring-sample:spring-boot-hello

DOCKER PUSH IMAGE TO REGISTRY

docker push rajeshg87/spring-sample
