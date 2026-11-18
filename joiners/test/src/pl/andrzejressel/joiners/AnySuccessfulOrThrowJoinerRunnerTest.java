package pl.andrzejressel.joiners;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import pl.andrzejressel.oneof.OneOf2;
import pl.andrzejressel.oneof.OneOf3;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AnySuccessfulOrThrowJoinerRunnerTest {

    @Nested
    class TwoArgumentsTest {
        @Test
        public void shouldReturnFirstResultIfItsFaster() throws Exception {
            var result = JoinerRunner.jdk.anySuccessfulOrThrow.run(
                    () -> "first",
                    () -> {
                        Thread.sleep(Integer.MAX_VALUE);
                        return "second";
                    }
            );

            assertEquals(OneOf2.first("first"), result);
        }

        @Test
        public void shouldReturnSecondResultIfItsFaster() throws Exception {
            var result = JoinerRunner.jdk.anySuccessfulOrThrow.run(
                    () -> {
                        Thread.sleep(Integer.MAX_VALUE);
                        return "first";
                    },
                    () -> "second"
            );

            assertEquals(OneOf2.second("second"), result);
        }

        @Test
        public void shouldThrowExceptionIfAllTasksFail() {
            var exception = assertThrows(ExecutionException.class, () -> JoinerRunner.jdk.anySuccessfulOrThrow.run(
                    () -> {
                        throw new RuntimeException("first");
                    },
                    () -> {
                        Thread.sleep(100);
                        throw new RuntimeException("second");
                    }
            ));

            var nestedException = exception.getCause();
            assertEquals("first", nestedException.getMessage());

        }
    }

    @Nested
    class ThreeArgumentsTest {
        @Test
        public void shouldReturnFirstResultIfItsFaster() throws Exception {
            var result = JoinerRunner.jdk.anySuccessfulOrThrow.run(
                    () -> "first",
                    () -> {
                        Thread.sleep(Integer.MAX_VALUE);
                        return "second";
                    },
                    () -> {
                        Thread.sleep(Integer.MAX_VALUE);
                        return "third";
                    }
            );

            assertEquals(OneOf3.first("first"), result);
        }

        @Test
        public void shouldReturnSecondResultIfItsFaster() throws Exception {
            var result = JoinerRunner.jdk.anySuccessfulOrThrow.run(
                    () -> {
                        Thread.sleep(Integer.MAX_VALUE);
                        return "first";
                    },
                    () -> "second",
                    () -> {
                        Thread.sleep(Integer.MAX_VALUE);
                        return "third";
                    }
            );

            assertEquals(OneOf3.second("second"), result);
        }

        @Test
        public void shouldReturnThirdResultIfItsFaster() throws Exception {
            var result = JoinerRunner.jdk.anySuccessfulOrThrow.run(
                    () -> {
                        Thread.sleep(Integer.MAX_VALUE);
                        return "first";
                    },
                    () -> {
                        Thread.sleep(Integer.MAX_VALUE);
                        return "second";
                    },
                    () -> "third"
            );

            assertEquals(OneOf3.third("third"), result);
        }

        @Test
        public void shouldThrowExceptionIfAllTasksFail() {
            var exception = assertThrows(ExecutionException.class, () -> JoinerRunner.jdk.anySuccessfulOrThrow.run(
                    () -> {
                        throw new RuntimeException("first");
                    },
                    () -> {
                        Thread.sleep(100);
                        throw new RuntimeException("second");
                    },
                    () -> {
                        Thread.sleep(100);
                        throw new RuntimeException("third");
                    }
            ));

            var nestedException = exception.getCause();
            assertEquals("first", nestedException.getMessage());

        }
    }

}
