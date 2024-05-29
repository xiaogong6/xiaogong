package com.xiaogong.arrayList;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: demo-java
 * @Description:
 * @Author: xiongke
 * @Create: 2024-03-29
 */
public class SubList {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>() {{
            add("abc");
            add("abcd");
            add("abcde");
        }};

        List<String> strings = list.subList(0, 1);
        System.out.println(strings);
    }

    @Test
    public void test() {
        List<String> sourceList = new ArrayList<>() {{
            add("1");
            add("2");
            add("3");
            add("4");
            add("5");
            add("6");
        }};

        List<String> subList = sourceList.subList(2, 5);

        System.out.println("sourceList ： " + sourceList);
        System.out.println("sourceList.subList(2, 5) 得到List ：");
        System.out.println("subList ： " + subList);

        subList.set(1, "666");

        System.out.println("subList.set(1,666) 得到List ：");
        System.out.println("subList ： " + subList);
        System.out.println("sourceList ： " + sourceList);
    }

    @Test
    public void test02(){
        List<String> sourceList = new ArrayList<>() {{
            add("H");
            add("O");
            add("L");
            add("L");
            add("I");
            add("S");
        }};

        List<String> subList = sourceList.subList(2, 5);

        System.out.println("sourceList ： " + sourceList);
        System.out.println("sourceList.subList(2, 5) 得到List ：");
        System.out.println("subList ： " + subList);

        subList.add("666");

        System.out.println("subList.add(666) 得到List ：");
        System.out.println("subList ： " + subList);
        System.out.println("sourceList ： " + sourceList);
    }

    @Test
    public void test03(){
        List<String> sourceList = new ArrayList<>() {{
            add("H");
            add("O");
            add("L");
            add("L");
            add("I");
            add("S");
        }};

        List<String> subList = sourceList.subList(2, 5);

        System.out.println("sourceList ： " + sourceList);
        System.out.println("sourceList.subList(2, 5) 得到List ：");
        System.out.println("subList ： " + subList);

        sourceList.add("666");

        System.out.println("sourceList.add(666) 得到List ：");
        System.out.println("sourceList ： " + sourceList);
        System.out.println("subList ： " + subList);
    }

}
