package pl.andrzejressel.joiners;

import io.arxila.javatuples.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

class ListUtils {

    private ListUtils() {
        throw new IllegalStateException("Utility class");
    }

    /// {@link AnySuccessfulOrThrowJoinerRunner#run(List)} uses `?` as a wildcard type, but we need to convert it to `Object` to be able to use it in the joiner. This method does that conversion.
    @SuppressWarnings("JavadocReference")
    static List<Callable<Object>> convertQuestionMarkListToObjectList(List<Callable<?>> list) {
        var result = new ArrayList<Callable<Object>>();
        for (var callable : list) {
            result.add(callable::call);
        }
        return result;
    }

    /// Zips the list with its index, so that we can use the index in the result
    static <T> List<Pair<Integer, T>> zipWithIndex(List<T> list) {
        var result = new ArrayList<Pair<Integer, T>>();
        for (var i = 0; i < list.size(); i++) {
            result.add(new Pair<>(i, list.get(i)));
        }
        return result;
    }

    /// Extracts Callable from `Pair<Integer, Callable<T>>` to `Callable<Pair<Integer, T>>` so that we can use the index in the result
    static <T> List<Callable<Pair<Integer, T>>> extractCallable(List<Pair<Integer, Callable<T>>> list) {
        var result = new ArrayList<Callable<Pair<Integer, T>>>();
        for (var pair : list) {
            result.add(() -> {
                var value = pair.value1().call();
                return new Pair<>(pair.value0(), value);
            });
        }
        return result;
    }

}
