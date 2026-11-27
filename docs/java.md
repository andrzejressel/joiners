# Java - Quick start

## Adding dependency

### Maven

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

## Joiners

### first successful value (`anySuccessfulOrThrow`)

`JoinerRunner.jdk.anySuccessfulOrThrow.run(...)` returns the first successful value or throws an exception if all tasks fail. Provided lambdas can have different return types, the result is wrapped in [OneOf](https://javadoc.io/doc/pl.andrzejressel/oneof/latest/pl.andrzejressel.oneof/pl/andrzejressel/oneof/package-summary.html) object that can be exhaustively matched on.

#### Usage

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

### all successful values (`allSuccessfulOrThrow`)

`JoinerRunner.jdk.allSuccessfulOrThrow.run(...)` returns all successful values or throws an exception if any task fails. The value is returned as [Tuple](https://javadoc.io/doc/io.arxila.javatuples/javatuples/latest/arxila.javatuples/io/arxila/javatuples/package-summary.html) object. Provided lambdas can have different return types - the tuple is parameterized by every single return type.

#### Usage

```java
import pl.andrzejressel.joiners.JoinerRunner;
import io.arxila.javatuples.Tuple2;

Tuple2<String, Integer> result = JoinerRunner.jdk.allSuccessfulOrThrow.run(
        () -> { Thread.sleep(100); return "1"; },
        () -> 2
);

return result;  // Result: Tuple2[value0=1, value1=2]
```