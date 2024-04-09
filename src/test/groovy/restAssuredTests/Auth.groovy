package restAssuredTests

import helpers.bookstore.CommonBaseSpecification
import org.testng.annotations.Test

class Auth extends CommonBaseSpecification {

    @Test
    void "get token test"() {
        Map payload = [
                userId           : "a178a104-e3c0-4f50-bbd5-3e4919f063e8",
                collectionOfIsbns: [[
                                            isbn: "9781449325863"
                                    ]]
        ]

        Map response = bookHelper().assignBook(userToken(), payload).bodyAsMap
        Map response2 = bookHelper().assignBook(userToken(), payload).bodyAsMap
        assert response
    }


}
