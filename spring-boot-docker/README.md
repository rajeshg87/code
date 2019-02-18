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
kubectl run mykubernetes-springboot --image=rajeshg87/spring-sample:spring-boot-hello --port=8085
kubectl expose deployment mykubernetes-springboot --name=myapp --port=80 --target-port=8085 --type=LoadBalancer 

kubectl get deployment
kubectl get service
kubectl delete service myapp
kubectl describe services/myapplb
kubectl get svc myapplb
