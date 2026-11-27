# Joiners

[![codecov](https://codecov.io/gh/andrzejressel/joiners/graph/badge.svg?token=O2VZ82B6L3)](https://codecov.io/gh/andrzejressel/joiners)

[![Maven Central Version](https://img.shields.io/maven-central/v/pl.andrzejressel/joiners?label=joiners)
](https://central.sonatype.com/artifact/pl.andrzejressel/joiners)
[![Maven Central Version](https://img.shields.io/maven-central/v/pl.andrzejressel/joiners4s_3?label=joiners4s)
](https://central.sonatype.com/artifact/pl.andrzejressel/joiners4s_3)

Joiners is a Java and Scala library that provides a simple way of running multiple tasks in parallel using [Structured Concurrency](https://openjdk.org/jeps/543).

[Documentation](https://joiners.readthedocs.io)

## Adding to your project

### Java

#### Maven

```xml
<dependency>
    <groupId>pl.andrzejressel</groupId>
    <artifactId>joiners</artifactId>
    <version>0.0.0-DEV</version>
</dependency>
```

### Gradle

```kotlin
dependencies {
    implementation("pl.andrzejressel:joiners:0.0.0-DEV")
}
```

## Quick start

### Java

```java
import pl.andrzejressel.joiners.JoinerRunner;
import pl.andrzejressel.oneof.OneOf2;

OneOf2<String, Integer> result = JoinerRunner.jdk.anySuccessfulOrThrow.run(
        () -> "1",
        () -> { Thread.sleep(100); return 2; }
);

return switch (result) {
    case OneOf2.First(var first) -> "Result is a String: " + first;
    case OneOf2.Second(var second) -> "Result is an Integer: " + second;
}; // Result: Result is a String: 1
```