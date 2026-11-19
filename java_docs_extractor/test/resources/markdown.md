# Java

```java
import pl.andrzejressel.joiners.JoinerRunner;

return JoinerRunner.jdk.anySuccessfulOrThrow.run(
        () -> "1",
        () -> 2
); // Result: Pair("asd", 2)
```