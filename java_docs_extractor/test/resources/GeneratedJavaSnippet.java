package pl.andrzejressel.javadocsextractor.generated;

import pl.andrzejressel.joiners.JoinerRunner;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeneratedJavaSnippet1473223028 {
    @Test
    public void testSnippet() throws Exception {
        var result = new java.util.concurrent.Callable<>() {
            @Override
            public Object call() throws Exception {
                return JoinerRunner.jdk.anySuccessfulOrThrow.run(
                        () -> "1",
                        () -> 2
                ); // Result: Pair("asd", 2)
            }
        };
        assertEquals("Pair(\"asd\", 2)", result.call().toString());
    }
}