cd consumer-order-openfeign
call gradle build
cd ..
docker image build -t order-openfeign-service:latest -f dockerfile-order-openfeign-noBuild .