package com.study.java8.lambda.example.stream.collector.model;

/**
 * @Author: H13995 魏喆
 * @Description: 
 * @Date: Created in 下午12:55 18/3/24
 * @Modified: by 
 */
public class Pair<K,V> {

    private K key;

    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
