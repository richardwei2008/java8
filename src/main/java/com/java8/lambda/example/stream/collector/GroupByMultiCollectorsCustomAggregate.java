package com.java8.lambda.example.stream.collector;

import com.google.common.collect.Lists;
import com.java8.lambda.example.stream.collector.collectors.ClaimProductTypeCollector;
import com.java8.lambda.example.stream.collector.collectors.PersonAttributeJoinerCollector;
import com.java8.lambda.example.stream.collector.model.Claim;
import com.java8.lambda.example.stream.collector.model.Pair;
import com.java8.lambda.example.stream.collector.model.Person;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @Description:
 * @Date: Created in 下午1:12 18/3/23
 * @Modified: by 
 */
public class GroupByMultiCollectorsCustomAggregate {


    public static void main(String[] args) {
        List<Person> persons = testData();

        System.out.println("========================");
        System.out.println("Method 1: generic ");
        Map<Integer, List<Object>> result1 = genericWithComplexCollector(persons);
        System.out.println(result1);

        System.out.println("========================");
        System.out.println("Method 2: generic list to object ");
        Map<Integer, Pair<String, String>> result2 = genericWithCustomComplexCollector(persons);
        System.out.println(result2);
    }

    private static Map<Integer,Pair<String,String>> genericWithCustomComplexCollector(List<Person> persons) {
        PersonAttributeJoinerCollector<Person> personAttributeJoinerCollector = new PersonAttributeJoinerCollector<>();
        Map map = persons.stream().collect(personAttributeJoinerCollector);
        return map;
    }


    private static Map<Integer, List<Object>> genericWithComplexCollector(List<Person> persons) {
        List<Collector<Person, ?, ?>> collectors = Arrays.asList(
                Collectors.mapping(x -> String.valueOf(x.getAge()), Collectors.joining(", ")),
                Collectors.mapping(Person::getName, Collectors.joining(", ")));

        /**
         * @see <a href="https://stackoverflow.com/questions/32071726/java-8-stream-groupingby-with-multiple-collectors">https://stackoverflow.com</a>
         *
         * A collector can only produce one object, but this object can hold multiple values.
         * You could return a Map for example where the map has an entry for each collector you are returning.
         *
         * You can use Collectors.of(HashMap::new, accumulator, combiner);
         *
         * Your accumulator would have a Map of Collectors
         * where the keys of the Map produced matches the name of the Collector.
         *
         * The combiner would need a way to combine multiple result esp when this is performed in parallel.
         */
        @SuppressWarnings("unchecked")
        Collector<Person, List<Object>, List<Object>> complexCollector = Collector.of(
                () -> collectors.stream().map(Collector::supplier)
                        .map(Supplier::get).collect(toList()),
                (list, e) -> IntStream.range(0, collectors.size()).forEach(
                        i -> ((BiConsumer<Object, Person>) collectors.get(i).accumulator()).accept(list.get(i), e)),
                (l1, l2) -> {
                    IntStream.range(0, collectors.size()).forEach(
                            i -> l1.set(i, ((BinaryOperator<Object>) collectors.get(i).combiner()).apply(l1.get(i), l2.get(i))));
                    return l1;
                },
                list -> {
                    IntStream.range(0, collectors.size()).forEach(
                            i -> list.set(i, ((Function<Object, Object>)collectors.get(i).finisher()).apply(list.get(i))));
                    return list;
                });

        Map<Integer, List<Object>> result = persons.stream().collect(
                groupingBy(Person::getGroup, complexCollector));
        return result;
    }

    private static List<Person> testData() {
        List<Person> persons = Lists.newArrayList();
        persons.add(new Person("Person One", 1, 18));
        persons.add(new Person("Person Two", 1, 20));
        persons.add(new Person("Person Three", 1, 30));
        persons.add(new Person("Person Four", 2, 30));
        persons.add(new Person("Person Five", 2, 29));
        persons.add(new Person("Person Six", 3, 18));
        return persons;
    }
}
