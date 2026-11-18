package pl.andrzejressel.joiners;

import io.arxila.javatuples.Tuple2;
import io.arxila.javatuples.Tuple3;
import io.arxila.javatuples.Tuple4;
import io.arxila.javatuples.Tuple5;
import io.arxila.javatuples.Tuple6;
import io.arxila.javatuples.Tuple7;
import io.arxila.javatuples.Tuple8;
import io.arxila.javatuples.Tuple9;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.StructuredTaskScope;

/**
 * Utility class giving access to utility methods for {@link StructuredTaskScope.Joiner#allSuccessfulOrThrow() allSuccessfulOrThrow()} joiner.
 */
public class AllSuccessfulOrThrowJoinerRunner {

    AllSuccessfulOrThrowJoinerRunner() {
    }

    /**
     * Runs the given callables in parallel and returns a {@link Tuple2} containing the results if both complete successfully. If any callable fails, it throws an exception.
     *
     * @param c1   First callable to run
     * @param c2   Second callable to run
     * @param <T1> Type of the result of the first callable
     * @param <T2> Type of the result of the second callable
     * @return A {@link Tuple2} containing the results of the callables if both complete successfully
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2> Tuple2<T1, T2> run(Callable<? extends T1> c1, Callable<? extends T2> c2) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2));
        //noinspection unchecked
        return Tuple2.of((T1) pair.get(0), (T2) pair.get(1));
    }

    /**
     * Runs the given callables in parallel and returns a {@link Tuple3} containing the results if all complete successfully. If any callable fails, it throws an exception.
     *
     * @param c1   First callable to run
     * @param c2   Second callable to run
     * @param c3   Third callable to run
     * @param <T1> Type of the result of the first callable
     * @param <T2> Type of the result of the second callable
     * @param <T3> Type of the result of the third callable
     * @return A {@link Tuple3} containing the results of the callables if all complete successfully
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3> Tuple3<T1, T2, T3> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3));
        //noinspection unchecked
        return Tuple3.of((T1) pair.get(0), (T2) pair.get(1), (T3) pair.get(2));
    }

    /**
     * Runs the given callables in parallel and returns a {@link Tuple4} containing the results if all complete successfully. If any callable fails, it throws an exception.
     *
     * @param c1   First callable to run
     * @param c2   Second callable to run
     * @param c3   Third callable to run
     * @param c4   Fourth callable to run
     * @param <T1> Type of the result of the first callable
     * @param <T2> Type of the result of the second callable
     * @param <T3> Type of the result of the third callable
     * @param <T4> Type of the result of the fourth callable
     * @return A {@link Tuple4} containing the results of the callables if all complete successfully
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4));
        //noinspection unchecked
        return Tuple4.of((T1) pair.get(0), (T2) pair.get(1), (T3) pair.get(2), (T4) pair.get(3));
    }

    /**
     * Runs the given callables in parallel and returns a {@link Tuple5} containing the results if all complete successfully. If any callable fails, it throws an exception.
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
     * @return A {@link Tuple5} containing the results of the callables if all complete successfully
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3, T4, T5> Tuple5<T1, T2, T3, T4, T5> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5));
        //noinspection unchecked
        return Tuple5.of((T1) pair.get(0), (T2) pair.get(1), (T3) pair.get(2), (T4) pair.get(3), (T5) pair.get(4));
    }

    /**
     * Runs the given callables in parallel and returns a {@link Tuple6} containing the results if all complete successfully. If any callable fails, it throws an exception.
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
     * @return A {@link Tuple6} containing the results of the callables if all complete successfully
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3, T4, T5, T6> Tuple6<T1, T2, T3, T4, T5, T6> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5, Callable<? extends T6> c6) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5, c6));
        //noinspection unchecked
        return Tuple6.of((T1) pair.get(0), (T2) pair.get(1), (T3) pair.get(2), (T4) pair.get(3), (T5) pair.get(4), (T6) pair.get(5));
    }

    /**
     * Runs the given callables in parallel and returns a {@link Tuple7} containing the results if all complete successfully. If any callable fails, it throws an exception.
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
     * @return A {@link Tuple7} containing the results of the callables if all complete successfully
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3, T4, T5, T6, T7> Tuple7<T1, T2, T3, T4, T5, T6, T7> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5, Callable<? extends T6> c6, Callable<? extends T7> c7) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5, c6, c7));
        //noinspection unchecked
        return Tuple7.of((T1) pair.get(0), (T2) pair.get(1), (T3) pair.get(2), (T4) pair.get(3), (T5) pair.get(4), (T6) pair.get(5), (T7) pair.get(6));
    }

    /**
     * Runs the given callables in parallel and returns a {@link Tuple8} containing the results if all complete successfully. If any callable fails, it throws an exception.
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
     * @return A {@link Tuple8} containing the results of the callables if all complete successfully
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5, Callable<? extends T6> c6, Callable<? extends T7> c7, Callable<? extends T8> c8) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5, c6, c7, c8));
        //noinspection unchecked
        return Tuple8.of((T1) pair.get(0), (T2) pair.get(1), (T3) pair.get(2), (T4) pair.get(3), (T5) pair.get(4), (T6) pair.get(5), (T7) pair.get(6), (T8) pair.get(7));
    }

    /**
     * Runs the given callables in parallel and returns a {@link Tuple9} containing the results if all complete successfully. If any callable fails, it throws an exception.
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
     * @return A {@link Tuple9} containing the results of the callables if all complete successfully
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T1, T2, T3, T4, T5, T6, T7, T8, T9> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> run(Callable<? extends T1> c1, Callable<? extends T2> c2, Callable<? extends T3> c3, Callable<? extends T4> c4, Callable<? extends T5> c5, Callable<? extends T6> c6, Callable<? extends T7> c7, Callable<? extends T8> c8, Callable<? extends T9> c9) throws ExecutionException, InterruptedException {
        var pair = run(List.of(c1, c2, c3, c4, c5, c6, c7, c8, c9));
        //noinspection unchecked
        return Tuple9.of((T1) pair.get(0), (T2) pair.get(1), (T3) pair.get(2), (T4) pair.get(3), (T5) pair.get(4), (T6) pair.get(5), (T7) pair.get(6), (T8) pair.get(7), (T9) pair.get(8));
    }

    /**
     * Runs the given callables in parallel and returns the result if all of them complete successfully. If any callable fails, it throws an exception.
     *
     * @param callables Coollection of callables to run in parallel
     * @return A list of results from the callables if all complete successfully
     * @param <T>       Type of the result of the callables
     * @throws ExecutionException   Wrapper exception over the exception thrown by the callable that failed
     * @throws InterruptedException If the current thread was interrupted while waiting for the callables to complete
     */
    public <T> List<T> run(Collection<? extends Callable<? extends T>> callables) throws ExecutionException, InterruptedException {
        List<Callable<?>> questionCallables = List.copyOf(callables);
        //noinspection unchecked
        return (List<T>) run0(questionCallables);
    }

    /**
     * Unsafe version of {{@link #run(Collection)}} method. It is used internally to avoid type erasure issues.
     */
    private List<Object> run0(List<Callable<?>> callables) throws ExecutionException, InterruptedException {
        var objectCallables = ListUtils.convertQuestionMarkListToObjectList(callables);
        var joiner = StructuredTaskScope.Joiner.allSuccessfulOrThrow();
        return JoinerRunner.joinCallables(joiner, objectCallables);
    }

}
