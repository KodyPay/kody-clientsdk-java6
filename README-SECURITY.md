# Running Kody client using JDK6 with TLSv1.2
The Kody client makes a secure connection to our system, this requires (at least) TLSv1.2
Note: earlier versions of TLS/SSL are not support.

JDK 6 does not support TLSv1.2 out of the box, so additional configuration is required to enable this.
In the following instructions, the `${JAVA_HOME}` directory is where you have installed JDK 6,
for example: `/usr/lib/jvm/java-6-openjdk`.  
These are instructions for Linux.
Similar instructions should apply to Windows,
however the location of the JAVA_HOME may change and path separators will use `\` instead of `/`.

## Download and Install JCE policy
The JCE policy enables extended security and replaces the existing policy files in the JDK,
it can only be downloaded from Oracle. To download the policy you need a (free) Oracle account.
An Oracle account can be created here: https://profile.oracle.com/myprofile/account/create-account.jspx

1. Download the JCE policy jar from Oracle, at following location: https://www.oracle.com/java/technologies/jce-6-download.html
2. Unpack the `jce_policy-6.zip` zip file, it should create a `jce` directory containing the following files:
    - `US_export_policy.jar`
    - `local_policy.jar`
3. Copy the 2 jar files into the following directory: `${JAVA_HOME}/jre/lib/security`

## Download and Install Bouncy Castle TLS files
1. Download the Bouncy Castle provider and TLS jar files, they are dependencies of the `samples` project
   and should be available in the `target/lib` directory after a build.
   Alternatively, they can be downloaded from: 
    - https://repo1.maven.org/maven2/org/bouncycastle/bcprov-jdk15to18/1.71/bcprov-jdk15to18-1.71.jar
    - https://repo1.maven.org/maven2/org/bouncycastle/bctls-jdk15to18/1.71/bctls-jdk15to18-1.71.jar
    - https://repo1.maven.org/maven2/org/bouncycastle/bcutil-jdk15to18/1.71/bcutil-jdk15to18-1.71.jar

2. Copy these JAR files into the following directory: `${JAVA_HOME}/jre/lib/ext`
    - `bcprov-jdk15to18-1.71.jar`
    - `bctls-jdk15to18-1.71.jar`
    - `bcutil-jdk15to18-1.71.jar`

## Modify the `java.security` configuration file
Modify the `java.security` file, this is normally located in the following directory: `${JAVA_HOME}/jre/lib/security`
(It may also be located in `/etc/java-6-openjdk/security`)  
Note: There is a snippet for the `java.security` in the following file: `java.security-snippet.txt`

1. Add the following lines, to add the Bouncy Castle security providers (above the existing `security.provider` entries):
   ```
    security.provider.1=org.bouncycastle.jce.provider.BouncyCastleProvider
    security.provider.2=org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
   ```

2. Modify the file to renumber the existing `security.provider` entries, for example:
   ```
   security.provider.3=sun.security.provider.Sun
   ...
   security.provider.11=sun.security.pkcs11.SunPKCS11 ${java.home}/lib/security/nss.cfg
   ```

3. Add the following line, to set the default SSL socket factory provider:
   ```
   ssl.SocketFactory.provider=org.bouncycastle.jsse.provider.SSLSocketFactoryImpl
   ```
