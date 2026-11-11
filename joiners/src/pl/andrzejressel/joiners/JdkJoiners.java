package pl.andrzejressel.joiners;

/**
 * Utility class giving access to utility methods for different standard library {@link java.util.concurrent.StructuredTaskScope.Joiner Joiners}.
 * Has a hidden constructor so it must be accessed through {@link Joiners#jdk}.
 */
public class JdkJoiners {
    JdkJoiners() {

    }

    /**
     * Utility methods for {@link java.util.concurrent.StructuredTaskScope.Joiner#anySuccessfulOrThrow() anySuccessfulOrThrow()} joiner.
     */
    public AnySuccessfulOrThrowJoiners anySuccessfulOrThrow = new AnySuccessfulOrThrowJoiners();
    
}
