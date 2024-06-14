package restAssuredTests.reqres

import general_helpers.StringHelper
import response_parser.RaResponse
import testNG.group_annotations.SmokeTest
import services.reqres.CommonBaseSpecification
import io.restassured.module.jsv.JsonSchemaValidator
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

import java.time.Instant

import static general_helpers.StringHelper.randomAlpha
import static general_helpers.ListHelper.getSortedListOfMaps
import static general_helpers.ListHelper.sortOrder.ASC

class ReqResTests extends CommonBaseSpecification {

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

    @Test()
    @SmokeTest
    void "get single users by id"() {
        Map user1 = UsersHelper().getSingleUser("1").bodyAsMap.data
        assert user1.first_name == "George"
    }

    @Test
    void "user json schema validation"() {
        JsonSchemaValidator validator = JsonSchemaValidator.matchesJsonSchemaInClasspath("ReqResUserSchema.json")
        RaResponse response = UsersHelper().getSingleUser("1")
        response.validateJsonSchema(validator)
    }

    @Test
    void "get users by page schema validation"() {
        JsonSchemaValidator validator = JsonSchemaValidator.matchesJsonSchemaInClasspath("ReqResUsersSchema.json")
        RaResponse response = UsersHelper().getListUsers([page: 1])
        response.validateJsonSchema(validator)
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
                assert headers.find { Object header -> header.name == expectedHeader.name }.value == expectedHeader.value
        }

    }

    @Test
    void "should return 404 on getting non-existent user"() {
        assert UsersHelper().getSingleUser(Integer.MAX_VALUE.toString()).status == 404
    }

    @Test
    void "verify users sorted by id ascending"() {
        List<Map> data = UsersHelper().getListUsers().bodyAsMap.data
        List<Map> expectedData = getSortedListOfMaps(new ArrayList<>(data), "id", ASC)
        assert data == expectedData
    }

    @Test
    void "verify cant log in with non-existent user"() {
        Map response = UsersHelper().loginUser(randomAlpha(10), randomAlpha(8)).bodyAsMap
        assert response.error == "user not found"
    }

    @Test
    void "verify user can be successfully registered and logined"() {
        Map responseRegistration = UsersHelper().registerUser(UsersHelper().TEST_USER_LOGIN, UsersHelper().TEST_USER_PASSWORD).bodyAsMap
        def responseLogin = UsersHelper().loginUser(UsersHelper().TEST_USER_LOGIN, UsersHelper().TEST_USER_PASSWORD)

        assert responseRegistration.id != null
        assert responseRegistration.token != null
        assert responseLogin.response.getStatusCode() == 200
        assert responseLogin.bodyAsMap.token == responseRegistration.token
    }

    @Test
    void "regex"() {
        String reg = "([a-f\\d]{8})-([a-f\\d]{4}-){3}([a-f\\d]{12})"
        assert StringHelper.randomUUID().matches(reg)
    }
}
