cd gateway
call gradle build
cd ..
docker image build -t gateway:latest -f dockerfile-gateway-noBuild .