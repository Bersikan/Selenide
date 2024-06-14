package sort;

import general_helpers.ListHelper;
import org.testng.annotations.Test;

import java.util.*;

import static general_helpers.ListHelper.sortOrder.ASC;
import static general_helpers.ListHelper.sortOrder.DESC;
import static general_helpers.ListHelper.getSortedListOfMaps;

public class SortingTest {

    @Test
    void test1() {
        List<Map<String, Object>> listOfMaps = new ArrayList<>();
        Map<String, Object> mapV1 = new HashMap<>();
        mapV1.put("id", 5);
        mapV1.put("name", "a");
        mapV1.put("createDate", "14/06/2024 12:00:00");

        Map<String, Object> mapV2 = new HashMap<>();
        mapV2.put("id", 4);
        mapV2.put("name", "v");
        mapV2.put("createDate", "16/06/2024 12:00:01");

        Map<String, Object> mapV3 = new HashMap<>();
        mapV3.put("id", 2);
        mapV3.put("name", "c");
        mapV3.put("createDate", "14/06/2024 12:00:00");

        Map<String, Object> mapV4 = new HashMap<>();
        mapV4.put("id", 3);
        mapV4.put("name", "v");
        mapV4.put("createDate", "15/06/2024 12:00:00");

        listOfMaps.add(mapV1);
        listOfMaps.add(mapV2);
        listOfMaps.add(mapV3);
        listOfMaps.add(mapV4);

        List<Object> a = new ArrayList<>();
        List<Object> b = new ArrayList<>();
        List<Object> c = new ArrayList<>();
        a.add("15/06/2024 12:00:03");
        a.add("15/06/2024 12:00:01");
        a.add("15/06/2024 12:00:02");

        b.add(5);
        b.add(1);
        b.add(3);

        c.add("5");
        c.add("1");
        c.add("3");

        List<Map<String, Object>> listOfMapsSorted1 = new ArrayList<>(getSortedListOfMaps(listOfMaps, "name", ASC));
        List<Map<String, Object>> listOfMapsSorted2 = new ArrayList<>(getSortedListOfMaps(listOfMaps, "name", ASC, "id", DESC));
        List<Map<String, Object>> listOfMapsSorted3 = new ArrayList<>(getSortedListOfMaps(listOfMaps, "name", DESC));
        List<Map<String, Object>> listOfMapsSorted4 = new ArrayList<>(getSortedListOfMaps(listOfMaps, "name", DESC, "id", DESC));

        List<Map<String, Object>> listOfMapsSorted5 = new ArrayList<>(getSortedListOfMaps(listOfMaps, "createDate", ASC, "id", ASC, "dd/MM/yyyy HH:mm:ss"));
        List<Map<String, Object>> listOfMapsSorted6 = new ArrayList<>(getSortedListOfMaps(listOfMaps, "createDate", DESC));

        assert !Objects.equals(listOfMapsSorted1, listOfMaps);
        assert !Objects.equals(listOfMapsSorted2, listOfMaps);
        assert !Objects.equals(listOfMapsSorted3, listOfMaps);
        assert !Objects.equals(listOfMapsSorted4, listOfMaps);
        assert !Objects.equals(listOfMapsSorted5, listOfMaps);
        assert !Objects.equals(listOfMapsSorted6, listOfMaps);

        System.out.println(ListHelper.getSortedList(a, ASC));
        System.out.println(ListHelper.getSortedList(b, ASC));
        System.out.println(ListHelper.getSortedList(c, ASC));

    }


}
