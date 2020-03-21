REGISTRY=azureremoterepository.azurecr.io
# the context of your local Kubernetes cluster
LOCAL_REGISTRY=localhost:32000
LOCAL_K8S_CLUSTER=microk8s
# the contect of your remote Kubernetes cluster
REMOTE_K8S_CLUSTER=cluster
# version/tag of the images that will be pushed to Docker Hub
#VERSION=0.0.1

API_PROJECT_NAME=petshop-api

# tag of the images that will be pushed for local development
TAG?=$(shell git rev-list HEAD --max-count=1 --abbrev-commit)
TAG_API=$(LOCAL_REGISTRY)/$(API_PROJECT_NAME):$(TAG)

TAG_API_R=$(REGISTRY)/$(API_PROJECT_NAME):$(TAG)

# local for docker
docker:
		DOCKER_BUILDKIT=1 docker build -t $(API_PROJECT_NAME) .
		docker-compose up


# local to microk8s
buildlocal:
		DOCKER_BUILDKIT=1 docker build -t $(TAG_API) .
#		docker system prune -f
		
pushlocal:
		docker push $(TAG_API)

deploylocaldb: uselocalcontext db

deploylocal: uselocalcontext host
		sed -e 's=petshop-api-image=$(TAG_API)=' deploy_api.yaml | kubectl apply -f -

cleandeploylocal: uselocalcontext clean

uselocalcontext:
		kubectl config use-context $(LOCAL_K8S_CLUSTER)

local: buildlocal pushlocal deploylocaldb deploylocal

# remotes to AKS
buildremote:
		DOCKER_BUILDKIT=1 docker build -t $(TAG_API_R) .
		docker system prune -f

pushremote:
		docker push $(TAG_API_R)

deployremotedb: useremotecontext db

deployremote: useremotecontext host
		sed -e 's=petshop-api-image=$(TAG_API_R)=' deploy_api.yaml | kubectl apply -f -
		
cleandeployremote: useremotecontext clean

useremotecontext:	
		kubectl config use-context $(REMOTE_K8S_CLUSTER)

remote: buildremote pushremote deployremotedb deployremote

# common
db:
		kubectl create -f deploy_db.yaml

host:
		kubectl create configmap hostname-config --from-literal=postgres_host=$(shell kubectl get svc postgres -o jsonpath="{.spec.clusterIP}")

clean:
		-kubectl delete -f deploy_api.yaml
		-kubectl delete cm hostname-config
		-kubectl delete -f deploy_db.yaml
