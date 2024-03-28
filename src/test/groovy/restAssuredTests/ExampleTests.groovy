package restAssuredTests


import org.testng.annotations.DataProvider
import org.testng.annotations.Test
import restAssuredConfigGroovy.BaseRestAssuredConfig
import verifyHelper.Verifier

import java.time.Instant

import static verifyHelper.Verifier.getSortedListOfMapsByTextEntity

class ExampleTests extends BaseRestAssuredConfig {


    @Test(dataProvider = "pages")
    void "get users by page"(int page, def range) {
        Map response = UsersHelper().getListUsers([page: page]).bodyAsMap
        assert response.page == page
        assert response.data.size() == 6
        assert response.data.id == range
    }

    @DataProvider(name = "pages")
    Object[][] pages() {
        return new Object[][]{
                [1, 1..6],
                [2, 7..12]
        }
    }

    @Test
    void "get single users by id"() {
        Map user1 = UsersHelper().getSingleUser("1").bodyAsMap.data
        assert user1.first_name == "George"
    }


    @Test
    void "post new users by page"() {
        Map postUser = UsersHelper().postUser(["name": "morpheus",
                                               "job" : "leader"]).bodyAsMap
        assert postUser.name == "morpheus"
        assert postUser.job == "leader"
        assert Instant.parse(postUser.createdAt) != null
    }

    @Test
    void "verify headers"() {
        List<Object> expectedHeaders = [[
                                                "name" : "Content-Type",
                                                "value": "application/json; charset=utf-8"]]
        List headers = UsersHelper().getListUsers([page: 1]).headersAsList

        expectedHeaders.forEach {
            Map expectedHeader ->
                assert headers.find {  Object header -> header.name == expectedHeader.name }.value == expectedHeader.value
        }

    }

    @Test
    void "should return 404 on getting non-existent user"() {
        assert UsersHelper().getSingleUser(Integer.MAX_VALUE.toString()).status == 404
    }

    @Test
    void "verify users sorted by id ascending"() {
        List<Map> data = UsersHelper().getListUsers().bodyAsMap.data
        List<Map> expectedData = getSortedListOfMapsByTextEntity(new ArrayList<>(data), "id", "asc")

        assert data == expectedData

    }
}
