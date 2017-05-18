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


## Please, don't sue us!

Sonatype, Nexus and Sonatype Nexus are trademarks of [Sonatype, Inc.](http://www.sonatype.org/)

Apache Maven and Maven are trademarks of the [Apache Software Foundation.](http://www.apache.org/) 
