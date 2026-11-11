package pl.andrzejressel.joiners;

import org.junit.jupiter.api.Test;
import pl.andrzejressel.oneof.OneOf2;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FirstSuccessfulJoinersTest {

    @Test
    public void shouldReturnFirstResultIfItsFaster() throws Exception {
        var result = Joiners.jdk.firstSuccessful.run(
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
        var result = Joiners.jdk.firstSuccessful.run(
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
        var exception = assertThrows(ExecutionException.class, () -> Joiners.jdk.firstSuccessful.run(
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
