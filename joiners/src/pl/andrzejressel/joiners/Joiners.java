package pl.andrzejressel.joiners;

/**
 * Utility class giving access to utility methods for different {@link java.util.concurrent.StructuredTaskScope.Joiner Joiners}.
 */
public class Joiners {

    private Joiners() {
    }

    /**
     * Utility methods for standard library {@link java.util.concurrent.StructuredTaskScope.Joiner Joiners}.
     */
    public static JdkJoiners jdk = new JdkJoiners();

}
