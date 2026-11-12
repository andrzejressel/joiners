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

    /**
     * Runs the given callables in parallel and returns the result of the first one that completes successfully. If all callables fail, an exception is thrown.
     *
     * @param c1   First callable to run
     * @param c2   Second callable to run
     * @param <T1> Type of the result of the first callable
     * @param <T2> Type of the result of the second callable
     * @return A {@link OneOf2} containing the result of the first callable that completes successfully, or an exception if all callables fail.
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2> OneOf2<T1, T2> run(Callable<? extends T1> c1, Callable<? extends T2> c2) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2));
        return OneOf2.fromIndex(pair.value0(), pair.value1());
    }

    /**
     * Runs the given callables in parallel and returns the result of the first one that completes successfully. If all callables fail, an exception is thrown.
     *
     * @param c1   First callable to run
     * @param c2   Second callable to run
     * @param c3   Third callable to run
     * @param <T1> Type of the result of the first callable
     * @param <T2> Type of the result of the second callable
     * @param <T3> Type of the result of the third callable
     * @return A {@link OneOf3} containing the result of the first callable that completes successfully, or an exception if all callables fail.
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3> OneOf3<T1, T2, T3> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3));
        return OneOf3.fromIndex(pair.value0(), pair.value1());
    }

    /**
     * Runs the given callables in parallel and returns the result of the first one that completes successfully. If all callables fail, an exception is thrown.
     *
     * @param c1   First callable to run
     * @param c2   Second callable to run
     * @param c3   Third callable to run
     * @param c4   Fourth callable to run
     * @param <T1> Type of the result of the first callable
     * @param <T2> Type of the result of the second callable
     * @param <T3> Type of the result of the third callable
     * @param <T4> Type of the result of the fourth callable
     * @return A {@link OneOf4} containing the result of the first callable that completes successfully, or an exception if all callables fail.
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3, T4> OneOf4<T1, T2, T3, T4> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4));
        return OneOf4.fromIndex(pair.value0(), pair.value1());
    }

    /**
     * Runs the given callables in parallel and returns the result of the first one that completes successfully. If all callables fail, an exception is thrown.
     *
     * @param c1   First callable to run
     * @param c2   Second callable to run
     * @param c3   Third callable to run
     * @param c4   Fourth callable to run
     * @param c5   Fifth callable to run
     * @param <T1> Type of the result of the first callable
     * @param <T2> Type of the result of the second callable
     * @param <T3> Type of the result of the third callable
     * @param <T4> Type of the result of the fourth callable
     * @param <T5> Type of the result of the fifth callable
     * @return A {@link OneOf5} containing the result of the first callable that completes successfully, or an exception if all callables fail.
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3, T4, T5> OneOf5<T1, T2, T3, T4, T5> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5));
        return OneOf5.fromIndex(pair.value0(), pair.value1());
    }

    /**
     * Runs the given callables in parallel and returns the result of the first one that completes successfully. If all callables fail, an exception is thrown.
     *
     * @param c1   First callable to run
     * @param c2   Second callable to run
     * @param c3   Third callable to run
     * @param c4   Fourth callable to run
     * @param c5   Fifth callable to run
     * @param c6   Sixth callable to run
     * @param <T1> Type of the result of the first callable
     * @param <T2> Type of the result of the second callable
     * @param <T3> Type of the result of the third callable
     * @param <T4> Type of the result of the fourth callable
     * @param <T5> Type of the result of the fifth callable
     * @param <T6> Type of the result of the sixth callable
     * @return A {@link OneOf6} containing the result of the first callable that completes successfully, or an exception if all callables fail.
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3, T4, T5, T6> OneOf6<T1, T2, T3, T4, T5, T6> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5, Callable<? extends T6> c6) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5, c6));
        return OneOf6.fromIndex(pair.value0(), pair.value1());
    }

    /**
     * Runs the given callables in parallel and returns the result of the first one that completes successfully. If all callables fail, an exception is thrown.
     *
     * @param c1   First callable to run
     * @param c2   Second callable to run
     * @param c3   Third callable to run
     * @param c4   Fourth callable to run
     * @param c5   Fifth callable to run
     * @param c6   Sixth callable to run
     * @param c7   Seventh callable to run
     * @param <T1> Type of the result of the first callable
     * @param <T2> Type of the result of the second callable
     * @param <T3> Type of the result of the third callable
     * @param <T4> Type of the result of the fourth callable
     * @param <T5> Type of the result of the fifth callable
     * @param <T6> Type of the result of the sixth callable
     * @param <T7> Type of the result of the seventh callable
     * @return A {@link OneOf7} containing the result of the first callable that completes successfully, or an exception if all callables fail.
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3, T4, T5, T6, T7> OneOf7<T1, T2, T3, T4, T5, T6, T7> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5, Callable<? extends T6> c6, Callable<? extends T7> c7) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5, c6, c7));
        return OneOf7.fromIndex(pair.value0(), pair.value1());
    }

    /**
     * Runs the given callables in parallel and returns the result of the first one that completes successfully. If all callables fail, an exception is thrown.
     *
     * @param c1   First callable to run
     * @param c2   Second callable to run
     * @param c3   Third callable to run
     * @param c4   Fourth callable to run
     * @param c5   Fifth callable to run
     * @param c6   Sixth callable to run
     * @param c7   Seventh callable to run
     * @param c8   Eighth callable to run
     * @param <T1> Type of the result of the first callable
     * @param <T2> Type of the result of the second callable
     * @param <T3> Type of the result of the third callable
     * @param <T4> Type of the result of the fourth callable
     * @param <T5> Type of the result of the fifth callable
     * @param <T6> Type of the result of the sixth callable
     * @param <T7> Type of the result of the seventh callable
     * @param <T8> Type of the result of the eighth callable
     * @return A {@link OneOf8} containing the result of the first callable that completes successfully, or an exception if all callables fail.
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3, T4, T5, T6, T7, T8> OneOf8<T1, T2, T3, T4, T5, T6, T7, T8> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5, Callable<? extends T6> c6, Callable<? extends T7> c7, Callable<? extends T8> c8) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5, c6, c7, c8));
        return OneOf8.fromIndex(pair.value0(), pair.value1());
    }

    /**
     * Runs the given callables in parallel and returns the result of the first one that completes successfully. If all callables fail, an exception is thrown.
     *
     * @param c1   First callable to run
     * @param c2   Second callable to run
     * @param c3   Third callable to run
     * @param c4   Fourth callable to run
     * @param c5   Fifth callable to run
     * @param c6   Sixth callable to run
     * @param c7   Seventh callable to run
     * @param c8   Eighth callable to run
     * @param c9   Ninth callable to run
     * @param <T1> Type of the result of the first callable
     * @param <T2> Type of the result of the second callable
     * @param <T3> Type of the result of the third callable
     * @param <T4> Type of the result of the fourth callable
     * @param <T5> Type of the result of the fifth callable
     * @param <T6> Type of the result of the sixth callable
     * @param <T7> Type of the result of the seventh callable
     * @param <T8> Type of the result of the eighth callable
     * @param <T9> Type of the result of the ninth callable
     * @return A {@link OneOf9} containing the result of the first callable that completes successfully, or an exception if all callables fail.
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3, T4, T5, T6, T7, T8, T9> OneOf9<T1, T2, T3, T4, T5, T6, T7, T8, T9> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5, Callable<? extends T6> c6, Callable<? extends T7> c7, Callable<? extends T8> c8, Callable<? extends T9> c9) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9));
        return OneOf9.fromIndex(pair.value0(), pair.value1());
    }

    /**
     * Runs the given callables in parallel and returns the result of the first one that completes successfully. If all callables fail, an exception is thrown.
     *
     * @param callables List of callables to run
     * @return A {@link Pair} containing the index of the first callable that completes successfully and its result, or an exception if all callables fail.
     * @throws ExecutionException  Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
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
