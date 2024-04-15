package restAssuredTests.bookstore

import response_parser.RaResponse
import services.bookstore.CommonBaseSpecification
import org.testng.annotations.Test

class Authorization extends CommonBaseSpecification {

    @Test
    void "get token test"() {
        Map payload = [
                userId           : "a178a104-e3c0-4f50-bbd5-3e4919f063e8",
                collectionOfIsbns: [[
                                            isbn: "9781449325863"
                                    ]]
        ]

        Map expectedResponse = ["code"   : "1207",
                                "message": "User Id not correct!"]

        RaResponse response = bookHelper().assignBook(userToken(), payload)
        assert response.status == 401
        assert response.bodyAsMap == expectedResponse
    }


}
