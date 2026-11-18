package pl.andrzejressel.joiners;

import io.arxila.javatuples.Tuple2;
import io.arxila.javatuples.Tuple3;
import io.arxila.javatuples.Tuple4;
import io.arxila.javatuples.Tuple5;
import io.arxila.javatuples.Tuple6;
import io.arxila.javatuples.Tuple7;
import io.arxila.javatuples.Tuple8;
import io.arxila.javatuples.Tuple9;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AllSuccessfulOrThrowJoinerRunnerTest {

    @Nested
    class TwoArgumentsTest {
        @Test
        public void shouldReturnBothResultsIfBothAreSuccessful() throws Exception {
            var result = JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> "first",
                    () -> "second"
            );

            assertEquals(Tuple2.of("first", "second"), result);
        }

        @Test
        public void shouldThrowExceptionIfFirstTaskFails() {
            var exception = assertThrows(ExecutionException.class, () -> JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> {
                        throw new RuntimeException("first");
                    },
                    () -> "second"
            ));

            var nestedException = exception.getCause();
            assertEquals("first", nestedException.getMessage());
        }
    }

    @Nested
    class ThreeArgumentsTest {
        @Test
        public void shouldReturnAllResultsIfAllAreSuccessful() throws Exception {
            var result = JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> 1,
                    () -> "second",
                    () -> true
            );

            assertEquals(Tuple3.of(1, "second", true), result);
        }

        @Test
        public void shouldThrowExceptionIfFirstTaskFails() {
            var exception = assertThrows(ExecutionException.class, () -> JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> {
                        throw new RuntimeException("first");
                    },
                    () -> "second",
                    () -> true
            ));

            assertEquals("first", exception.getCause().getMessage());
        }
    }

    @Nested
    class FourArgumentsTest {
        @Test
        public void shouldReturnAllResultsIfAllAreSuccessful() throws Exception {
            var result = JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> 1,
                    () -> "second",
                    () -> true,
                    () -> 4L
            );

            assertEquals(Tuple4.of(1, "second", true, 4L), result);
        }

        @Test
        public void shouldThrowExceptionIfFirstTaskFails() {
            var exception = assertThrows(ExecutionException.class, () -> JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> {
                        throw new RuntimeException("first");
                    },
                    () -> "second",
                    () -> true,
                    () -> 4L
            ));

            assertEquals("first", exception.getCause().getMessage());
        }
    }

    @Nested
    class FiveArgumentsTest {
        @Test
        public void shouldReturnAllResultsIfAllAreSuccessful() throws Exception {
            var result = JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> 1,
                    () -> "second",
                    () -> true,
                    () -> 4L,
                    () -> 5.0
            );

            assertEquals(Tuple5.of(1, "second", true, 4L, 5.0), result);
        }

        @Test
        public void shouldThrowExceptionIfFirstTaskFails() {
            var exception = assertThrows(ExecutionException.class, () -> JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> {
                        throw new RuntimeException("first");
                    },
                    () -> "second",
                    () -> true,
                    () -> 4L,
                    () -> 5.0
            ));

            assertEquals("first", exception.getCause().getMessage());
        }
    }

    @Nested
    class SixArgumentsTest {
        @Test
        public void shouldReturnAllResultsIfAllAreSuccessful() throws Exception {
            var result = JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> 1,
                    () -> "second",
                    () -> true,
                    () -> 4L,
                    () -> 5.0,
                    () -> '6'
            );

            assertEquals(Tuple6.of(1, "second", true, 4L, 5.0, '6'), result);
        }

        @Test
        public void shouldThrowExceptionIfFirstTaskFails() {
            var exception = assertThrows(ExecutionException.class, () -> JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> {
                        throw new RuntimeException("first");
                    },
                    () -> "second",
                    () -> true,
                    () -> 4L,
                    () -> 5.0,
                    () -> '6'
            ));

            assertEquals("first", exception.getCause().getMessage());
        }
    }

    @Nested
    class SevenArgumentsTest {
        @Test
        public void shouldReturnAllResultsIfAllAreSuccessful() throws Exception {
            var result = JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> 1,
                    () -> "second",
                    () -> true,
                    () -> 4L,
                    () -> 5.0,
                    () -> '6',
                    () -> (byte) 7
            );

            assertEquals(Tuple7.of(1, "second", true, 4L, 5.0, '6', (byte) 7), result);
        }

        @Test
        public void shouldThrowExceptionIfFirstTaskFails() {
            var exception = assertThrows(ExecutionException.class, () -> JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> {
                        throw new RuntimeException("first");
                    },
                    () -> "second",
                    () -> true,
                    () -> 4L,
                    () -> 5.0,
                    () -> '6',
                    () -> (byte) 7
            ));

            assertEquals("first", exception.getCause().getMessage());
        }
    }

    @Nested
    class EightArgumentsTest {
        @Test
        public void shouldReturnAllResultsIfAllAreSuccessful() throws Exception {
            var result = JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> 1,
                    () -> "second",
                    () -> true,
                    () -> 4L,
                    () -> 5.0,
                    () -> '6',
                    () -> (byte) 7,
                    () -> (short) 8
            );

            assertEquals(Tuple8.of(1, "second", true, 4L, 5.0, '6', (byte) 7, (short) 8), result);
        }

        @Test
        public void shouldThrowExceptionIfFirstTaskFails() {
            var exception = assertThrows(ExecutionException.class, () -> JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> {
                        throw new RuntimeException("first");
                    },
                    () -> "second",
                    () -> true,
                    () -> 4L,
                    () -> 5.0,
                    () -> '6',
                    () -> (byte) 7,
                    () -> (short) 8
            ));

            assertEquals("first", exception.getCause().getMessage());
        }
    }

    @Nested
    class NineArgumentsTest {
        @Test
        public void shouldReturnAllResultsIfAllAreSuccessful() throws Exception {
            var result = JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> 1,
                    () -> "second",
                    () -> true,
                    () -> 4L,
                    () -> 5.0,
                    () -> '6',
                    () -> (byte) 7,
                    () -> (short) 8,
                    () -> 9
            );

            assertEquals(Tuple9.of(1, "second", true, 4L, 5.0, '6', (byte) 7, (short) 8, 9), result);
        }

        @Test
        public void shouldThrowExceptionIfFirstTaskFails() {
            var exception = assertThrows(ExecutionException.class, () -> JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    () -> {
                        throw new RuntimeException("first");
                    },
                    () -> "second",
                    () -> true,
                    () -> 4L,
                    () -> 5.0,
                    () -> '6',
                    () -> (byte) 7,
                    () -> (short) 8,
                    () -> 9
            ));

            assertEquals("first", exception.getCause().getMessage());
        }
    }

    @Nested
    class ListOfCallablesTest {
        @Test
        public void shouldReturnAllResultsIfAllAreSuccessful() throws Exception {
            var result = JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    List.of(
                            () -> 1,
                            () -> "second",
                            () -> true
                    )
            );

            assertEquals(List.of(1, "second", true), result);
        }

        @Test
        public void shouldThrowExceptionIfFirstTaskFails() {
            var exception = assertThrows(ExecutionException.class, () -> JoinerRunner.jdk.allSuccessfulOrThrow.run(
                    List.of(
                            () -> {
                                throw new RuntimeException("first");
                            },
                            () -> "second",
                            () -> true
                    )
            ));

            assertEquals("first", exception.getCause().getMessage());
        }
    }
}
