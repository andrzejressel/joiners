package pl.andrzejressel.joiners;

import io.arxila.javatuples.Pair;
import pl.andrzejressel.oneof.OneOf2;
import pl.andrzejressel.oneof.OneOf3;
import pl.andrzejressel.oneof.OneOf4;
import pl.andrzejressel.oneof.OneOf5;
import pl.andrzejressel.oneof.OneOf6;
import pl.andrzejressel.oneof.OneOf7;
import pl.andrzejressel.oneof.OneOf8;
import pl.andrzejressel.oneof.OneOf9;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

/**
 * Utility class giving access to utility methods for {@link StructuredTaskScope.Joiner#anySuccessfulOrThrow() anySuccessfulOrThrow()} joiner.
 */
public class AnySuccessfulOrThrowJoiners {

    AnySuccessfulOrThrowJoiners() {

    }

    public <T1, T2> OneOf2<T1, T2> run(Callable<? extends T1> c1, Callable<? extends T2> c2) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2));
        return OneOf2.fromIndex(pair.value0(), pair.value1());
    }

    public <T1, T2, T3> OneOf3<T1, T2, T3> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3));
        return OneOf3.fromIndex(pair.value0(), pair.value1());
    }

    public <T1, T2, T3, T4> OneOf4<T1, T2, T3, T4> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4));
        return OneOf4.fromIndex(pair.value0(), pair.value1());
    }

    public <T1, T2, T3, T4, T5> OneOf5<T1, T2, T3, T4, T5> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5));
        return OneOf5.fromIndex(pair.value0(), pair.value1());
    }

    public <T1, T2, T3, T4, T5, T6> OneOf6<T1, T2, T3, T4, T5, T6> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5, Callable<? extends T6> c6) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5, c6));
        return OneOf6.fromIndex(pair.value0(), pair.value1());
    }

    public <T1, T2, T3, T4, T5, T6, T7> OneOf7<T1, T2, T3, T4, T5, T6, T7> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5, Callable<? extends T6> c6, Callable<? extends T7> c7) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5, c6, c7));
        return OneOf7.fromIndex(pair.value0(), pair.value1());
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8> OneOf8<T1, T2, T3, T4, T5, T6, T7, T8> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5, Callable<? extends T6> c6, Callable<? extends T7> c7, Callable<? extends T8> c8) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5, c6, c7, c8));
        return OneOf8.fromIndex(pair.value0(), pair.value1());
    }

    public <T1, T2, T3, T4, T5, T6, T7, T8, T9> OneOf9<T1, T2, T3, T4, T5, T6, T7, T8, T9> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5, Callable<? extends T6> c6, Callable<? extends T7> c7, Callable<? extends T8> c8, Callable<? extends T9> c9) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9));
        return OneOf9.fromIndex(pair.value0(), pair.value1());
    }

    private Pair<Integer, Object> run(List<Callable<?>> callables) throws ExecutionException, InterruptedException {
        try (var scope = StructuredTaskScope.open(StructuredTaskScope.Joiner.<Pair<Integer, Object>>anySuccessfulOrThrow())) {
            for (var i = 0; i < callables.size(); i++) {
                var callable = callables.get(i);
                var finalI = i;
                scope.fork(() -> {
                    var result = callable.call();
                    return new Pair<>(finalI, result);
                });
            }

            return scope.join();
        }
    }

}
