cd message-consumer
call gradle build
cd ..
docker image build -t message-consumer:latest -f dockerfile-message-consumer-noBuild .