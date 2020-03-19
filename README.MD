docker build -t cemp2703/app-java-devops:0.1 .
docker-compose up -d      
docker-compose down
docker-compose ps

https://documenter.getpostman.com/view/6266128/SzS1SoVS?version=latest

docker run -t postman/newman:alpine run https://www.postman.com/collections/7298bfbcc1b9d9139551


docker run -v $(pwd):/etc/newman -t postman/newman:alpine run api-calculadora.postman_collection.json -e CLOUD.postman_environment.json --env-var 'url=http://localhost'

docker rm $(docker ps -aq)

