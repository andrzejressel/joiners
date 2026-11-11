package pl.andrzejressel.joiners;

import org.junit.jupiter.api.Test;
import pl.andrzejressel.oneof.OneOf2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FirstSuccessfulJoinersTest {

    @Test
    public void shouldReturnFirstResultIfItsFaster() throws Exception {
        var joiner = new FirstSuccessfulJoiners();

        var result = joiner.run(
                () -> {
                    Thread.sleep(100);
                    return "first";
                },
                () -> {
                    Thread.sleep(200);
                    return "second";
                }
        );

        assertEquals(OneOf2.first("first"), result);
    }

    @Test
    public void shouldReturnSecondResultIfItsFaster() throws Exception {
        var joiner = new FirstSuccessfulJoiners();

        var result = joiner.run(
                () -> {
                    Thread.sleep(200);
                    return "first";
                },
                () -> {
                    Thread.sleep(100);
                    return "second";
                }
        );

        assertEquals(OneOf2.second("second"), result);
    }

    @Test
    public void shouldThrowExceptionIfAllTasksFail() {
        var joiner = new FirstSuccessfulJoiners();

        assertThrows(RuntimeException.class, () -> joiner.run(
                    () -> {
                        throw new RuntimeException("first");
                    },
                    () -> {
                        throw new RuntimeException("second");
                    }
            ));
    }

}
