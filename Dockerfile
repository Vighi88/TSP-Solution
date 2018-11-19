# Pull base image
From tomcat:8-jre8
 
# Copy to images tomcat path
COPY HPSE-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/HPSE.war

#Permission for war file 
RUN CHMOD 777 /usr/local/tomcat/webapps/HPSE.war
