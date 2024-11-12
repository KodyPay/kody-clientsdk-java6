FROM openjdk:6-jdk

WORKDIR /app
RUN mkdir /app/samples
ENV JAVA_HOME /usr/lib/jvm/java-6-openjdk-armhf
ENV JCE_POLICY_DIR=$JAVA_HOME/jre/lib/security
ENV JVM_EXT_DIR=$JAVA_HOME/jre/lib/ext

# copy JCE policy files and update java.security to add:

# security.provider.1=org.bouncycastle.jce.provider.BouncyCastleProvider
# security.provider.2=org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
# ssl.SocketFactory.provider=org.bouncycastle.jsse.provider.SSLSocketFactoryImpl

COPY jce/java.security /etc/java-6-openjdk/security
COPY jce/local_policy.jar $JCE_POLICY_DIR
COPY jce/US_export_policy.jar $JCE_POLICY_DIR
COPY target/lib/bcprov-jdk15to18-1.71.jar $JVM_EXT_DIR
COPY target/lib/bctls-jdk15to18-1.71.jar $JVM_EXT_DIR
COPY target/lib/bcutil-jdk15to18-1.71.jar $JVM_EXT_DIR

COPY target/lib/kody-clientsdk-java6-1.0-SNAPSHOT.jar /app
COPY target/samples-1.0-SNAPSHOT.jar /app
COPY dev-config.properties /app/samples
COPY logging.properties /app/samples

ENTRYPOINT ["java", "-Djava.util.logging.config.file=samples/logging.properties", "-cp", "kody-clientsdk-java6-1.0-SNAPSHOT.jar:samples-1.0-SNAPSHOT.jar", "terminal.TerminalMain"]
