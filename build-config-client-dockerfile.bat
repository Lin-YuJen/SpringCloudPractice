cd config-client
call gradle build
cd ..
docker image build -t config-client:latest -f dockerfile-config-client-noBuild .