cd message-provider
call gradle build
cd ..
docker image build -t message-provider:latest -f dockerfile-message-provider-noBuild .