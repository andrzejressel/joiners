package pl.andrzejressel.joiners;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.StructuredTaskScope;

/**
 * Utility class giving access to utility methods for different {@link java.util.concurrent.StructuredTaskScope.Joiner Joiners}.
 */
public class Joiners {

    private Joiners() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Utility methods for standard library {@link java.util.concurrent.StructuredTaskScope.Joiner Joiners}.
     */
    public static JdkJoiners jdk = new JdkJoiners();

    /**
     * Utility method for joining a list of {@link Callable} tasks using a given {@link StructuredTaskScope.Joiner}.
     * Internally it creates a new {@link StructuredTaskScope} with the provided joiner, forks all the tasks, and then joins the results.
     *
     * <p>
     * <strong>Example</strong>
     * <p>
     * {@snippet lang = java:
     *   List<Callable<Integer>> tasks = List.of(
     *       () -> {Thread.sleep(1000); return 1;},
     *       () -> {Thread.sleep(2000); return 2;},
     *       () -> {Thread.sleep(3000); return 3;}
     *   );
     *   Integer result = Joiners.joinCallables(StructuredTaskScope.Joiner.anySuccessfulOrThrow(), tasks);
     *   System.out.println(result); // Output: 1
     *}
     *
     * @param joiner The joiner to use for joining the results of the tasks.
     * @param tasks The list of tasks to be executed and joined.
     * @return The result of joining the results of the tasks using the provided joiner.
     * @param <T> Return type of the tasks.
     * @param <R> Return type of the joiner.
     * @param <R_X> Exception type thrown by the joiner.
     * @throws R_X Exception thrown by the joiner.
     * @throws InterruptedException If the current thread is interrupted while waiting for the tasks to complete.
     */
    public static <T, R, R_X extends Throwable> R joinCallables(StructuredTaskScope.Joiner<T, R, R_X> joiner, List<Callable<T>> tasks) throws R_X, InterruptedException {
        try (var scope = StructuredTaskScope.open(joiner)) {
            for (var task : tasks) {
                scope.fork(task);
            }
            return scope.join();
        }
    }

}
