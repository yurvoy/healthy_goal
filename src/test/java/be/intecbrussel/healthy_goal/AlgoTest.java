package be.intecbrussel.healthy_goal;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

public class AlgoTest {

    @Test
    public void mapTest () {
        Map<Long, Double> map = new TreeMap<>();
        map.put(123456L, 59D);
        map.put(123458L, 60D);
        map.put(123457L, 57D);
        map.put(123459L, 53D);
        map.put(123455L, 62D);
        map.put(123460L, 63D);
        map.put(123461L, 67D);
        map.put(123462L, 82D);
        map.put(123463L, 86D);

        map.remove(123463L);

        int lastIndex = map.size()-1;
        System.out.println(map.values().toArray()[lastIndex]);

        map.remove(123462L);

        lastIndex = map.size()-1;
        System.out.println(map.values().toArray()[lastIndex]);

        map.remove(123461L);

        lastIndex = map.size()-1;
        System.out.println(map.values().toArray()[lastIndex]);

        map.remove(123460L);

        lastIndex = map.size()-1;
        System.out.println(map.values().toArray()[lastIndex]);

        map.remove(123455L);

        lastIndex = map.size()-1;
        System.out.println(map.values().toArray()[lastIndex]);
    }
}
