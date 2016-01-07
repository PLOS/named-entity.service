mvn deploy:deploy-file \
		-DpomFile="pom.xml" \
		-DgroupId="org.plos" \
		-Dversion="0.13.0-SNAPSHOT" \
		-DartifactId="ned-client" \
    -Dpackaging=jar \
    -Dfile="../java/target/ned-client-0.13.0-SNAPSHOT.jar" \
    -DrepositoryId=ambra \
    -Durl=sftp://maven.ambraproject.org/home/maven2/repository/release

