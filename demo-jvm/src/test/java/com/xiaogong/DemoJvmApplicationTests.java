package com.xiaogong;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class DemoJvmApplicationTests {

    @Test
    void contextLoads() {
        List<ObjectTest> objects = new ArrayList<>();

        objects.add(new ObjectTest(1,"a"));
        objects.add(new ObjectTest(1,"b"));
        objects.add(new ObjectTest(2,"a"));
        objects.add(new ObjectTest(3,"a"));
        objects.add(new ObjectTest(3,"c"));

        Map<Integer, List<ObjectTest>> collect = objects.stream().collect(Collectors.groupingBy(ObjectTest::getId));

        collect.forEach((key, value) -> {
            System.out.println(key);
            value.forEach(objectTest -> {
                System.out.println(objectTest.getId());
            });

        });
    }

    @Test
    public void test(){
        System.out.println(new ObjectTest().getId()!=1);
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ObjectTest{

    public int id;

    public String name;

}