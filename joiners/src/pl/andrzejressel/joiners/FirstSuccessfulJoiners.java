package pl.andrzejressel.joiners;

import io.arxila.javatuples.Pair;
import pl.andrzejressel.oneof.OneOf2;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

public class FirstSuccessfulJoiners {

    FirstSuccessfulJoiners() {

    }

    public <T1, T2> OneOf2<T1, T2> run(Callable<? extends T1> c1, Callable<? extends T2> c2) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2));
        return OneOf2.fromIndex(pair.value0(), pair.value1());
    }

    private Pair<Integer, Object> run(List<Callable<?>> callables) throws ExecutionException, InterruptedException {
        try (var scope = StructuredTaskScope.open(
                StructuredTaskScope.Joiner.<Pair<Integer, Object>>anySuccessfulOrThrow()
        )) {
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
