echo Build all projects and associated docker images
cd assessment
echo Assessment project =====
mvn clean install
docker build -t mediscreen/assessment .
cd ../mediscreen
echo Mediscreen project =====
mvn clean install
docker build -t mediscreen/mediscreen .
cd ../notes
echo Notes project =====
mvn clean install
docker build -t mediscreen/notes .
cd ../patient
echo Patient project =====
mvn clean install
docker build -t mediscreen/patient .