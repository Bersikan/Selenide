package nested

import general_helpers.MapHelper
import org.testng.annotations.Test

class NestedSearchTest {


    @Test
    void "nested search test"() {
        def map = [
                "key5": "zxc",
                "key" : ["asd1", "asd5"],
                "key2": ["key": "asd2"],
                "key3": ["key4": ["key": "asd3"]],
        ]

        def a = MapHelper.nestedSearch(map, "key")
        print(a)
    }
}
