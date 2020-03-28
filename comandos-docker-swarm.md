## Inicar docker swarm
docker swarm init

## Ejecutar stacks en docker swarm

### ejecutar un stack
docker stack deploy --compose-file docker-compose-stack-swarm.yml calculadora-stack
### listar los stacks
docker stack ls
### listar los servicios del stack
docker service ls
### eliminar los stacks
docker stack rm student-stack