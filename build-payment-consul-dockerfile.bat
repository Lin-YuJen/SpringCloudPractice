cd provider-payment-consul
call gradle build
cd ..
docker image build -t payment-service-consul:latest -f dockerfile-payment-consul-noBuild .