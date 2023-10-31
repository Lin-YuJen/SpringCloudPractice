cd provider-payment-zookeeper
call gradle build
cd ..
docker image build -t payment-service-zookeeper:latest -f dockerfile-payment-zookeeper-noBuild .