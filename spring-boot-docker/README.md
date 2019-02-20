DOCKER IMAGE BUILD 

docker build -t docker-spring-boot .

DOCKER RUN IMAGE

docker run --name rajesh-sample -p 8085:8085 docker-spring-boot

DOCKER TAG IMAGE

docker tag 73692afd539b rajeshg87/spring-sample:spring-boot-hello

DOCKER PUSH IMAGE TO REGISTRY

docker push rajeshg87/spring-sample

MINIKUBE CMD

minikube stop
minikube delete
minikube --memory 8192 --cpus 2 start

minikube ip
sudo kubectl run myapp --image=rajeshg87/spring-sample:spring-boot-hello --port=8085
sudo kubectl expose deployment/myapp --type="LoadBalancer" --port=8085

kubectl get deployments
kubectl get service

kubectl describe services/myapp
sudo kubectl scale --replicas=2 deployment/myapp
kubectl get pods