scp -i "kp_cesar_ohio.pem" docker-compose-stack-swarm.yml ec2-user@ec2-18-221-215-133.us-east-2.compute.amazonaws.com:/home/ec2-user

## Inicar docker swarm


## Ejecutar stacks en docker swarm

### ejecutar un stack
docker stack deploy --compose-file docker-compose.yml student-stack
### listar los stacks
docker stack ls
### listar los servicios del stack
docker service ls
### eliminar los stacks
docker stack rm student-stack