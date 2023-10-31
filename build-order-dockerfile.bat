cd consumer-order
call gradle build
cd ..
docker image build -t order-service:latest -f dockerfile-order-noBuild .