package com.java8.lambda.example.stream.map;

import com.java8.lambda.example.stream.Staff;
import com.java8.lambda.example.stream.StaffPublic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Richard on 17/11/3.
 */
public class BeforeJava8StaffConverter {

    public static void main(String[] args) {

        List<Staff> staff = Arrays.asList(
                new Staff("mkyong", 30, new BigDecimal(10000)),
                new Staff("jack", 27, new BigDecimal(20000)),
                new Staff("lawrence", 33, new BigDecimal(30000))
        );

        List<StaffPublic> result = convertToStaffPublic(staff);
        System.out.println(result);
    }

    private static List<StaffPublic> convertToStaffPublic(List<Staff> staff) {

        List<StaffPublic> result = new ArrayList<>();

        for (Staff temp : staff) {

            StaffPublic obj = new StaffPublic();
            obj.setName(temp.getName());
            obj.setAge(temp.getAge());
            if ("mkyong".equals(temp.getName())) {
                obj.setExtra("this field is for mkyong only!");
            }

            result.add(obj);
        }

        return result;
    }
}
