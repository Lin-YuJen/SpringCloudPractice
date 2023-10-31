cd provider-payment
call gradle build
cd ..
docker image build -t payment-service:latest -f dockerfile-payment-noBuild .