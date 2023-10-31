cd consumer-order-openfeign-resilience4j
call gradle build
cd ..
docker image build -t order-openfeign-resilience4j-service:latest -f dockerfile-order-openfeign-resilience4j-noBuild .