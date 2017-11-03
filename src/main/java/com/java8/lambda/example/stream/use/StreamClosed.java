package com.java8.lambda.example.stream.use;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by Richard on 17/11/3.
 */
public class StreamClosed {

    public static void main(String[] args){

        String[] array = {"a", "b", "c", "d", "e"};
        Stream<String> stream = Arrays.stream(array);

        // loop a stream
        stream.forEach(x -> System.out.print(x));

        // reuse it to filter again! throws IllegalStateException
        long count = stream.filter(x -> "b".equals(x)).count();
        System.out.println(count);
    }

    /**
     * Exception in thread "main" java.lang.IllegalStateException:
     *      stream has already been operated upon or closed
     *  at java.util.stream.AbstractPipeline.<init>(AbstractPipeline.java:203)
     *  at java.util.stream.ReferencePipeline.<init>(ReferencePipeline.java:94)
     *      at java.util.stream.ReferencePipeline$StatelessOp.<init>(ReferencePipeline.java:618)
     *      at java.util.stream.ReferencePipeline$2.<init>(ReferencePipeline.java:163)
     *  at java.util.stream.ReferencePipeline.filter(ReferencePipeline.java:162)
     *  at com.java8.lambda.example.stream.use.StreamClosed.main(StreamClosed.java:20)
     *
     */


}
