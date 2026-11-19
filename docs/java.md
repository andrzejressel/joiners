# Java

```java
import pl.andrzejressel.joiners.JoinerRunner;

return JoinerRunner.jdk.anySuccessfulOrThrow.run(
        () -> "1",
        () -> { Thread.sleep(100); return 2; }
); // Result: First[value=1]
```