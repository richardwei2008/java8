package com.study.java8.lambda.example.stream.collector.collectors;

import com.study.java8.lambda.example.stream.collector.model.Claim;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class ClaimProductTypeCollector<T extends Claim> implements Collector<T, Map, Map> {
 
    private Set<Claim.PRODUCT_TYPE> requiredTypes = new HashSet<>();
 
    public Set<Claim.PRODUCT_TYPE> getRequiredTypes() {
        return requiredTypes;
    }

    /**
     * supplier – returns a function, that takes no arguments,
     * and returns an empty instance of the collection class you want
     * to put your collected elements into.
     *
     * e.g. if you are ultimately collecting your elements into a set,
     * the supplier function will return an empty set.
     *
     * @return
     */
    @Override
    public Supplier<Map> supplier() {
        return () -> new HashMap<>();
    }

    /**
     * accumulator – returns a function that takes two arguments,
     * the first is the collection that you are building up,
     * the second is the element being processed.
     *
     * The accumulator function processes each element into the target collection.
     *
     * @return
     */
    @Override
    public BiConsumer<Map, T> accumulator() {
        return (map, claim) -> {
            if (map.get(claim.getProductType()) == null) {
                map.put(claim.getProductType(), claim);
            }
        };
    }


    /**
     * finisher – returns a function that allows you
     * to perform a final transformation on your collection, if required.
     *
     * In many cases, you won’t need to transform the collection any further,
     * so you will just use an identity function here.
     *
     * @return
     */
    @Override
    public Function<Map, Map> finisher() {
        return Function.identity();
    }

    /**
     * combiner – only required for parallel processing of your stream.
     * If you envisage running this operation across multiple processors / cores,
     * then your combiner contains the logic to combine results from each parallel operation.
     *
     * @return
     */
    @Override
    public BinaryOperator<Map> combiner() {
        return null;
    }

    /**
     * characteristics – allows you to specify the characteristics of the collector
     * so that that it can be invoked safely and optimally.
     *
     * e.g. specifying Characteristics.IDENTITY_FINISH lets Java know that
     * because you aren’t performing a final transformation,
     * it doesn’t even need to invoke your finisher function.
     *
     * @return
     */
    @Override
    public Set<Characteristics> characteristics() {
        return Collections.singleton(Characteristics.IDENTITY_FINISH);
    }
}