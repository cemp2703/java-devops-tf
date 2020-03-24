## Proyecto realizado usando .yml de cloud formation de aws para montar jenkins
### Instalación local de jenkins para pruebas.


### Copiando archivos a ec2


## Copia de archivo compose a Aplication Server.

scp -i "kp_cesar_ohio.pem" docker-compose-stack-swarm.yml ec2-user@ec2-18-221-215-133.us-east-2.compute.amazonaws.com:/home/ec2-user

## Copia de .pem a JenkinsServer

scp -i "kp_cesar_ohio.pem" kp_cesar_ohio.pem ec2-user@ec2-3-21-204-119.us-east-2.compute.amazonaws.com:/home/ec2-user

## copiar la pem en la carpeta y dar permisos de lectura para el usuario jenkins
/var/lib/jenkins/pems/

chmod 444 kp_cesar_ohio.pem

### Algunos comandos usados

docker build -t cemp2703/app-java-devops:0.1 .
docker-compose up -d      
docker-compose down
docker-compose ps

https://documenter.getpostman.com/view/6266128/SzS1SoVS?version=latest

docker run -t postman/newman:alpine run https://www.postman.com/collections/7298bfbcc1b9d9139551


docker run -v $(pwd)/e2e/newman:/etc/newman -t --network host postman/newman:alpine run api-calculadora.postman_collection.json -e CLOUD.postman_environment.json --env-var 'url=http://localhost'

docker rm $(docker ps -aq)