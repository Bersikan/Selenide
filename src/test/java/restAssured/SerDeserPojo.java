package restAssured;



import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class SerDeserPojo {

    @Test
    public void sereliasiationTest() throws JsonProcessingException {
        String id = "1";
        String json = given().get("https://reqres.in" + "/api/users/" + id).getBody().asPrettyString();
        Map<String, Object> mapJson  = new ObjectMapper().readValue(json, HashMap.class);
        assert mapJson != null;
    }


}
