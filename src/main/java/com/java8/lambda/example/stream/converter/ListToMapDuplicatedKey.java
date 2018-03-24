package com.java8.lambda.example.stream.converter;

import com.java8.lambda.example.stream.Hosting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Richard on 17/11/3.
 */
public class ListToMapDuplicatedKey {

    public static void main(String[] args) {
        List<Hosting> list = new ArrayList<>();
        list.add(new Hosting(1, "liquidweb.com", 80000));
        list.add(new Hosting(2, "linode.com", 90000));
        list.add(new Hosting(3, "digitalocean.com", 120000));
        list.add(new Hosting(4, "aws.amazon.com", 200000));
        list.add(new Hosting(5, "mkyong.com", 1));

        list.add(new Hosting(6, "linode.com", 100000)); // new line

        // key = name, value = websites,
        // but the key 'linode' is duplicated!?
        Map<String, Long> result1 = list.stream().collect(
                Collectors.toMap(Hosting::getName, Hosting::getWebSites)
        );



        /**
         * Solution
         */
        // Map<String, Long> result1 = list.stream().collect(
        //        Collectors.toMap(Hosting::getName, Hosting::getWebSites,
        //                (oldValue, newValue) -> oldValue
        //        )
        // );

        System.out.println("Result 1 : " + result1);
    }

    /**
     * Exception in thread "main" java.lang.IllegalStateException: Duplicate key 90000
     *      at java.util.stream.Collectors.lambda$throwingMerger$0(Collectors.java:133)
     *      at java.util.HashMap.merge(HashMap.java:1253)
     *      at java.util.stream.Collectors.lambda$toMap$58(Collectors.java:1320) <1 internal calss>
     *          at java.util.stream.ReduceOps$3ReducingSink.accept(ReduceOps.java:169)
     *      at java.util.ArrayList$ArrayListSpliterator.forEachRemaining(ArrayList.java:1374) <4 internal calss>
     *          at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:481)
     *          at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:471)
     *          at java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:708)
     *          at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
     *      at java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:499)
     *      at com.java8.lambda.example.stream.converter.ListToMapDuplicatedKey.main(ListToMapDuplicatedKey.java:27)
     *
     */
}
