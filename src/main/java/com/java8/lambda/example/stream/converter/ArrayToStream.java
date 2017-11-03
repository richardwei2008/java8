package com.java8.lambda.example.stream.converter;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Richard on 17/11/3.
 */
public class ArrayToStream {

    public static void main(String[] args) {

        String[] array = {"a", "b", "c", "d", "e"};

        // Arrays.stream
        Stream<String> stream1 = Arrays.stream(array);
        stream1.forEach(x -> System.out.println(x));

        // Stream.of
        Stream<String> stream2 = Stream.of(array);
        stream2.forEach(x -> System.out.println(x));
    }

    /**
     * For object arrays, the Stream.of method is calling the Arrays.stream internally
     */

    // Review the JDK source code internally
    /**
     * Returns a sequential {@link Stream} with the specified array as its
     * source.
     *
     * @param <T> The type of the array elements
     * @param array The array, assumed to be unmodified during use
     * @return a {@code Stream} for the array
     * @since 1.8
     */
    // public static <T> Stream<T> stream(T[] array) {
    //    return stream(array, 0, array.length);
    // }

    /**
     * Returns a sequential ordered stream whose elements are the specified values.
     *
     * @param <T> the type of stream elements
     * @param values the elements of the new stream
     * @return the new stream
     */
    // @SafeVarargs
    // @SuppressWarnings("varargs") // Creating a stream from an array is safe
    // public static<T> Stream<T> of(T... values) {
    //    return Arrays.stream(values);
    // }


}
