# nexus-rest-client
A simple Java library to query Maven™ artifacts on Sonatype Nexus™ 2.x

## Dependencies

This library depends on:

* Java 8 (compile)
* Spring Boot
* Spring Framework
* Jersey Client
* sl4j-api and slf4j-log4j12
* etc

For more information about dependencies, please see the [Dependencies and Limitations](https://github.com/oobj/nexus-rest-client/wiki/Dependencies-And-Limitations) Wiki page.

## Setting your Nexus credentials

First of all, you must have to set some informations about your Nexus instalation, like URL address, login and password.


To configure these information, you must have to define the following properties during your Maven build:

* `nexus.url`
* `nexus.username`
* `nexus.password`

Theses keys will be replaced in the `nexus.properties` file.
To assure a high level of security, this user credentials can be read-only.

A fashioned way to describe this properties is via Maven profiles.

For more information about Maven properties during the build, please read the [Maven documentation](http://maven.apache.org/guides/introduction/introduction-to-profiles.html).

## Importing

To import the library to your project using Maven, you must add the following snippet to your `pom.xml`:

```xml
<dependency>
    <groupId>br.com.oobj</groupId>
    <artifactId>nexus-rest-client</artifactId>
    <version>${nexus-rest-client.version}</version>
</dependency>
```

Where `nexus-rest-client.version` is the version chosen by you.

## And finally, using the library

The main interface on this library is the `NexusService` interface.

To use the service, you probally have to inject this in your code, like this:

```java
@Component
public MySpringComponent {
    
    private final NexusService nexusService;

    @Inject
    public MySpringComponent(NexusService nexusService) {
        this.nexusService = nexusService;
    }
}
```

So, you have these options:

* `nexusService.getMavenArtifactContent`
* `nexusService.getMavenArtifactContentAsync`
* `nexusService.queryMavenArtifactPomModel`
* `nexusService.queryMavenArtifactMetaData`
* `nexusService.queryMavenArtifactDetails`

Each option above is explained in the [Features](https://github.com/oobj/nexus-rest-client/wiki/Features) Wiki page.


 
