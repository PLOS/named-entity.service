mvn deploy:deploy-file \
		-DgroupId="org.plos" \
		-Dversion="0.11.0-SNAPSHOT" \
		-DartifactId="ned-client" \
    -Dpackaging=jar \
    -Dfile="java/target/ned-client-0.11.0-SNAPSHOT.jar" \
    -DrepositoryId=ambra \
    -Durl=sftp://maven.ambraproject.org/home/maven2/repository/release
