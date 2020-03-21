# Sample petshop REST API

#### Local usage in IDE

App can be run from IDE, the 'dev' profile uses in memory H2 db.

#### Docker with psql:
```
#build docker image and run with docker-compose
DOCKER_BUILDKIT=1 docker build -t petshop_api
docker-compose up

# or use make
make docker
```

#### Local Kubernetes:
* tested with [microk8s](https://microk8s.io/)
* requires a local docker repository on `localhost:30000`

for intermediary steps please check out `Makefile`
```
# builds docker image, pushes local to repo, deploys psql, deploys api 
make local
```

#### Remote Kubernetes
setup required for Azure services (repository, AKS..)
```
# builds docker image, pushes to remote repo, deploys psql, deploys api 
make remote
```
