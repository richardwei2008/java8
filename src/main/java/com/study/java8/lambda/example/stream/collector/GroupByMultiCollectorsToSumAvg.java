package com.study.java8.lambda.example.stream.collector;

import com.google.common.collect.Lists;
import com.study.java8.lambda.example.stream.collector.model.Data;
import com.study.java8.lambda.example.stream.collector.model.Person;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

/**
 * @Description:
 * @Date: Created in 上午9:58 18/3/24
 * @Modified: by 
 */
public class GroupByMultiCollectorsToSumAvg {

    public static void main(String[] args) {
        List<Person> persons = testData();

        System.out.println("========================");
        System.out.println("Method 1: concrete ");
        Map<Integer, Data> result1 = concreteWithCollectingAndThen(persons);
        System.out.println(result1);
        System.out.println();


        System.out.println("========================");
        System.out.println("Method 2: generic collectors ");
        Map<Integer, List<Object>> result2 = genericWithComplexCollector(persons);
        System.out.println(result2);
        System.out.println();

        System.out.println("========================");
        System.out.println("Method 3: Count Sum with generic reducers ");
        Map<String, Integer> result3 = genericWithReducers(persons);
        System.out.println(result3);
    }

    private static Map<String, Integer> genericWithReducers(List<Person> persons) {
        List<BiFunction<Map<String, Integer>, Person, Map<String, Integer>>> listOfReducers = new ArrayList<>();

        listOfReducers.add((m, p) -> addMap(m, "Count", Optional.ofNullable(m.get("Count")).orElse(0) + 1));
        listOfReducers.add((m, p) -> addMap(m, "Sum", Optional.ofNullable(m.get("Sum")).orElse(0) + p.getAge()));

        BiFunction<Map<String, Integer>, Person, Map<String, Integer>> applyList
                = (mapin, p) -> {
            Map<String, Integer> mapout = mapin;
            for (BiFunction<Map<String, Integer>, Person, Map<String, Integer>> f : listOfReducers) {
                mapout = f.apply(mapout, p);
            }
            return mapout;
        };
        BinaryOperator<Map<String, Integer>> combineMaps
                = (map1, map2) -> {
            Map<String, Integer> mapout = new HashMap<>();
            mapout.putAll(map1);
            mapout.putAll(map2);
            return mapout;
        };
        Map<String, Integer> map
                = persons
                .stream()
                .reduce(new HashMap<String, Integer>(),
                        applyList, combineMaps);
        return map;
    }

    public static <K, V> Map<K, V> addMap(Map<K, V> map, K k, V v) {
        Map<K, V> mapout = new HashMap<K, V>();
        mapout.putAll(map);
        mapout.put(k, v);
        return mapout;
    }

    private static Map<Integer, List<Object>> genericWithComplexCollector(List<Person> persons) {
        List<Collector<Person, ?, ?>> collectors = Arrays.asList(
                Collectors.averagingInt(Person::getAge),
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

    /**
     * @see java.util.DoubleSummaryStatistics
     * @param persons
     * @return
     */
    private static Map<Integer,Data> concreteWithCollectingAndThen(List<Person> persons) {
        return persons.stream().collect(
                groupingBy(Person::getGroup,
                        collectingAndThen(summarizingDouble(Person::getAge),
                                dss -> new Data(dss.getAverage(), dss.getSum()))));
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
