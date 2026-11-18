package pl.andrzejressel.joiners;

/**
 * Utility class giving access to utility methods for different standard library {@link java.util.concurrent.StructuredTaskScope.Joiner Joiners}.
 * Has a hidden constructor so it must be accessed through {@link JoinerRunner#jdk}.
 */
public class JdkJoinerRunner {
    JdkJoinerRunner() {

    }

    /**
     * Utility methods for {@link java.util.concurrent.StructuredTaskScope.Joiner#anySuccessfulOrThrow() anySuccessfulOrThrow()} joiner.
     */
    public AnySuccessfulOrThrowJoinerRunner anySuccessfulOrThrow = new AnySuccessfulOrThrowJoinerRunner();

    /**
     * Utility methods for {@link java.util.concurrent.StructuredTaskScope.Joiner#allSuccessfulOrThrow() allSuccessfullOrThrow()} joiner.
     */
    public AllSuccessfulOrThrowJoinerRunner allSuccessfulOrThrow = new AllSuccessfulOrThrowJoinerRunner();

}
