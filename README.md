# Spring Batch Demo

The [Spring framework](https://spring.io/) is a widely used open source Enterprise grade application framework, primarily used for software integrations. 

This demo project showcasing [Spring batch](https://spring.io/projects/spring-batch) as a solution to read a CSV, call a coupa web service and write a CSV containing the merged results

## Setup
This project is the source code for an open source Java Application

### To build the application from source
You must have apache [Maven](https://maven.apache.org/install.html) and a [Java Runtime Environment](https://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html) installed on your computer. At a linux command line type the following
*  mvn clean install

The above command will result in a self contained spring boot Jar being created at target/api-to-csv-0.0.1-SNAPSHOT.jar

### To run
This application can be run on a server, stand alone via maven spring boot plug, stand alone as a Java Jar.

This application uses [externalized configuration](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html) to avoid exposing sensitive information to the internet. 
As a result these config items must be provided to the runtime: API URL, API KEY. We do this below using command line arguments 

with Maven via spring boot plugin:
*  mvn spring-boot:run -Dspring-boot.run.arguments=--coupa.api.key={{ YOUR API KEY GOES HERE }}--coupa.api.url={{ YOUR API URL GOES HERE }}

with Java
* java -jar target/api-to-csv-0.0.1-SNAPSHOT.jar --coupa.api.key={{ YOUR API KEY GOES HERE }}--coupa.api.url={{ YOUR API URL GOES HERE }}

### Examine results
Upon execution the application will:
 
*  pull the sample-input.csv file contained in the Resources folder
*  for each line in the input file, make a RESTful web service call to /invoices/{invoiceId} on the the provided coupa.api.url with the coupa.api.key
*  map the results to the output CSV format
*  write the results to /output/outputData.csv from the project root directory
