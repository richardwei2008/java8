package com.study.java8.lambda.example.stream.map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

/**
 * @Description:
 * @Date: Created in 上午8:49 18/3/24
 * @Modified: by 
 */
public class MapMultipleElements {

    /**
     * from
     */
    public static class MultiDataPoint {
        private String timestamp;
        private Map<String, Number> keyToData;

        public MultiDataPoint(String timestamp, Map<String, Number> keyToData) {
            this.timestamp = timestamp;
            this.keyToData = keyToData;
        }

        @Override
        public String toString() {
            return "MultiDataPoint{" +
                    "timestamp='" + timestamp + '\'' +
                    ", keyToData=" + keyToData +
                    '}';
        }
    }


    /**
     * to
     */
    public static class DataSet {
        public String key;
        List<DataPoint> dataPoints;

        public DataSet(String key, List<DataPoint> dataPoints) {
            this.key = key;
            this.dataPoints = dataPoints;
        }

        @Override
        public String toString() {
            return "DataSet{" +
                    "key='" + key + '\'' +
                    ", dataPoints=" + dataPoints +
                    '}';
        }
    }

    public static class DataPoint{
        String timeStamp;
        Number data;

        public DataPoint(String timeStamp, Number data) {
            this.timeStamp = timeStamp;
            this.data = data;
        }

        @Override
        public String toString() {
            return "DataPoint{" +
                    "timeStamp=" + timeStamp +
                    ", data=" + data +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println("Default Method In Collection Framework");
        List<MultiDataPoint> testData = testData();
        System.out.println("========== Test Data");
        System.out.println(testData);
        Collection<DataSet> result1 = defaultMethodInCollectionFramework(testData);
        System.out.println("========== Result");
        System.out.println(result1);
        System.out.println();

        System.out.println("Stream API with flatten and intermediate data structure");
        System.out.println("========== Test Data");
        System.out.println(testData);
        Collection<DataSet> result2 = streamAPIWithFlattenAndIntermediateDataStructure(testData);
        System.out.println("========== Result");
        System.out.println(result2);
        System.out.println();

        System.out.println("Stream API with map merging");
        System.out.println("========== Test Data");
        System.out.println(testData);
        Collection<DataSet> result3 = streamAPIWithMapMerging(testData);
        System.out.println("========== Result");
        System.out.println(result3);
        System.out.println();

    }

    private static List<MultiDataPoint> testData() {
        List<MultiDataPoint> dataPoints = Lists.newArrayList();
        Map<String, Number> map1 = Maps.newHashMap();
        map1.put("key1", 1);
        map1.put("key2", 123);
        dataPoints.add(new MultiDataPoint("20180301", map1));
        Map<String, Number> map2 = Maps.newHashMap();
        map2.put("key3", 12);
        map2.put("key4", 423);
        dataPoints.add(new MultiDataPoint("20180324", map2));
        return dataPoints;
    }


    private static Collection<DataSet> defaultMethodInCollectionFramework(
            List<MultiDataPoint> multiDataPoints) {
        Map<String, DataSet> result = new HashMap<>();
        multiDataPoints.forEach(pt ->
                pt.keyToData.forEach((key, value) ->
                        result.computeIfAbsent(
                                key, k -> new DataSet(k, new ArrayList<>()))
                                .dataPoints.add(new DataPoint(pt.timestamp, value))));
        return result.values();
    }

    /**
     * use anonymous inner class
     * @return
     */
    private static Collection<DataSet> streamAPIWithFlattenAndIntermediateDataStructure(
            List<MultiDataPoint> multiDataPoints) {
        return multiDataPoints.stream()
                .flatMap(mdp -> mdp.keyToData.entrySet().stream().map(e ->
                        new Object() {
                            String key = e.getKey();
                            DataPoint dataPoint = new DataPoint(mdp.timestamp, e.getValue());
                        }))
                .collect(
                        collectingAndThen(
                                groupingBy(t -> t.key, mapping(t -> t.dataPoint, Collectors.toList())),
                                m -> m.entrySet().stream().map(e -> new DataSet(e.getKey(), e.getValue()))
                                        .collect(toList())));
    }

    private static Collection<DataSet> streamAPIWithMapMerging(
            List<MultiDataPoint> multiDataPoints) {
        return multiDataPoints.stream()
                .map(mdp -> mdp.keyToData.entrySet().stream()
                        .collect(toMap(e -> e.getKey(), e -> asList(new DataPoint(mdp.timestamp, e.getValue())))))
                .reduce(new HashMap<>(), mapMerger())
                .entrySet().stream()
                .map(e -> new DataSet(e.getKey(), e.getValue()))
                .collect(toList());
    }

    private static <K, V> BinaryOperator<Map<K, List<V>>> mapMerger() {
        return (lhs, rhs) -> {
            Map<K, List<V>> result = new HashMap<>();
            lhs.forEach((key, value) -> result.computeIfAbsent(key, k -> new ArrayList<>()).addAll(value));
            rhs.forEach((key, value) -> result.computeIfAbsent(key, k -> new ArrayList<>()).addAll(value));
            return result;
        };
    }

}
