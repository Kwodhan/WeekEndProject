cd data
rm -R *
cd ..
./run-hsqldb-server.sh
mvn dependency:copy-dependencies
cd data
java -cp ../target/dependency/hsqldb-2.3.4.jar org.hsqldb.Server
