package com.study.java8.lambda.example.stream.collector;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * @Description:
 * @Date: Created in 上午9:22 18/3/24
 * @Modified: by 
 */
public class GroupByListLimit {

    public static void main(String[] args) {
        List<StudentClass> studentClasses = testData();
        System.out.println("========================");
        System.out.println(studentClasses);

        System.out.println("========================");
        System.out.println("Method 1: custom collector ");
        Map<String, List<StudentClass>> groupByTeachers1 =
                studentClasses.stream()
                        .collect(groupingBy(
                                StudentClass::getTeacher,
                                limitingTo(1)
                        ));
        System.out.println(groupByTeachers1);


        System.out.println("Method 2: collectingAndThen ");
        Map<String, List<StudentClass>> groupByTeachers2 =
                studentClasses.stream()
                        .collect(groupingBy(
                                StudentClass::getTeacher,
                                collectingAndThen(
                                        toList(),
                                        l -> l.stream().limit(2).collect(toList()))));
        System.out.println(groupByTeachers2);
    }


    private static <T> Collector<T, ?, List<T>> limitingTo(int limit) {
        return Collector.of(
                ArrayList::new,
                (l, e) -> { if (l.size() < limit) l.add(e); },
                (l1, l2) -> {
                    l1.addAll(l2.subList(0, Math.min(l2.size(), Math.max(0, limit - l1.size()))));
                    return l1;
                }
        );
    }



    private static List<StudentClass> testData() {
        List<StudentClass> studentClasses = Lists.newArrayList();
        studentClasses.add(new StudentClass("Kumar", 101, "Intro to Web"));
        studentClasses.add(new StudentClass("White", 102, "Advanced Java"));
        studentClasses.add(new StudentClass("Kumar", 101, "Intro to Cobol"));
        studentClasses.add(new StudentClass("White", 101, "Intro to Web"));
        studentClasses.add(new StudentClass("White", 102, "Advanced Web"));
        studentClasses.add(new StudentClass("Sargent", 106, "Advanced Web"));
        studentClasses.add(new StudentClass("Sargent", 103, "Advanced Web"));
        studentClasses.add(new StudentClass("Sargent", 104, "Advanced Web"));
        studentClasses.add(new StudentClass("Sargent", 105, "Advanced Web"));
        return studentClasses;
    }

    private static class StudentClass {
        private String teacher;
        private int room;
        private String course;

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public int getRoom() {
            return room;
        }

        public void setRoom(int room) {
            this.room = room;
        }

        public String getCourse() {
            return course;
        }

        public void setCourse(String course) {
            this.course = course;
        }

        public StudentClass(String teacher, int room, String course) {
            this.teacher = teacher;
            this.room = room;
            this.course = course;
        }

        @Override
        public String toString() {
            return "StudentClass{" +
                    "name='" + teacher + '\'' +
                    ", room=" + room +
                    ", course='" + course + '\'' +
                    '}';
        }
    }
}
