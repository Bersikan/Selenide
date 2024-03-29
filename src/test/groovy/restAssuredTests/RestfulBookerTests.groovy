package restAssuredTests


import helpers.restfulbooker.BaseRestAssuredConfig
import org.testng.annotations.Test

class RestfulBookerTests extends BaseRestAssuredConfig {


    @Test
    void "check user token is successfully generated"(){
        assert userToken()  == null
    }

}
