cd ./model
call mvn clean install
cd ..
cd ./kjar
call mvn clean install
cd ..
cd ./service
call mvn clean install
cd ..
pause