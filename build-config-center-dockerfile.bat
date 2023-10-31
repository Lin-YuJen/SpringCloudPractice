cd config-center
call gradle build
cd ..
docker image build -t config-center:latest -f dockerfile-config-center-noBuild .