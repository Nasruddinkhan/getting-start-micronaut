## Micronaut 4.10.10 Documentation

- [User Guide](https://docs.micronaut.io/4.10.10/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.10.10/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.10.10/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)
---

## Build Commands

- Compile the project:

```bash
./mvnw compile
```

- Package the application:

```bash
./mvnw package
```

- Run the tests:

```bash
./mvnw test
```

## Micronaut Dependencies

This project is built on the Micronaut platform parent:

```xml
<parent>
  <groupId>io.micronaut.platform</groupId>
  <artifactId>micronaut-parent</artifactId>
  <version>4.10.10</version>
</parent>
```

Main application dependencies:

- `io.micronaut:micronaut-http-server-netty`
- `io.micronaut.serde:micronaut-serde-jackson`
- `ch.qos.logback:logback-classic`

Test dependencies:

- `io.micronaut:micronaut-http-client`
- `io.micronaut.test:micronaut-test-junit5`
- `org.junit.jupiter:junit-jupiter-api`
- `org.junit.jupiter:junit-jupiter-engine`

Annotation processor dependencies used by the Maven compiler plugin:

- `io.micronaut:micronaut-inject-java`
- `io.micronaut:micronaut-http-validation`
- `io.micronaut.serde:micronaut-serde-processor`
- `io.micronaut:micronaut-inject`

Annotation processing should stay enabled for this project because Micronaut relies on compile-time generated metadata.

## Docker Image Build

This project uses the Jib Maven plugin to build a local Docker image without needing a `Dockerfile`.

- Build the local Docker image:

```bash
./mvnw compile jib:dockerBuild
```

- Build with a custom image name:

```bash
./mvnw compile jib:dockerBuild -Dimage=my-app:latest
```

- Default generated image name:

```bash
getting-start-micronaut:0.1
```

- [Micronaut Maven Plugin documentation](https://micronaut-projects.github.io/micronaut-maven-plugin/latest/)
## Feature maven-enforcer-plugin documentation


- [https://maven.apache.org/enforcer/maven-enforcer-plugin/](https://maven.apache.org/enforcer/maven-enforcer-plugin/)


## Feature micronaut-aot documentation


- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)


## Feature serialization-jackson documentation


- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)


# getting-start-micronaut
