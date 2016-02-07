# INSTALLATION

## Java
- Download Java 8 from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
- Execute the installer

## Maven
- Download Maven 3 from https://maven.apache.org/download.cgi
- Unzip/Untar the archive
- Set the environment variable M2_HOME to the directory where you have unzip/untar the archive
- Add M2_HOME/bin to your PATH variable

## Wildfly
- Download Wildfly from http://wildfly.org/downloads/
- Unzip/Untar the archive



# EXECUTION

## Running the Arquillian integration test
- Goto ${webservice-mock_HOME}
- Execute 'mvn clean install -Pit'



# Running it in Wildfly Application server

## Start Wildfly
- Goto ${WILDFLY_HOME}/bin
- Execute './standalone.sh'

## Deploy the application
- Goto ${webservice-mock_HOME}
- Execute 'mvn clean install -Pdeploy'

## Verify the deployment
If you execute an HTTP GET request to http://localhost:8080/webservice-mock/CommentsService/Comments?wsdl, you should receive the WSDL file for this SOAP web serice

## Test the application
curl -i -X POST -H 'Content-Type: application/xml;charset=UTF-8' -d '<?xml version="1.0" encoding="UTF-8"?><soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ns="http://www.company.de/Service/1"><soapenv:Header/><soapenv:Body><ns:getComment>1</ns:getComment></soapenv:Body></soapenv:Envelope>' http://localhost:8080/webservice-mock/CommentsService/Comments