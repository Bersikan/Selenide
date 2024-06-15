package restAssuredTests.swapi

import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.testng.annotations.Test
import response_parser.RaResponse
import services.swapi.CommonBaseSpecification
import services.swapi.Swapi

class SwapiTests extends CommonBaseSpecification{

    @Test
    void "test people"(){
        RaResponse swapiPeople = Swapi.getPeople()
        Map<String,Object> body = swapiPeople.bodyAsMap

        assert swapiPeople.status ==200
        assert body.count == 82
        assert body.results[0].name == "Luke Skywalker"
        assert body.results.size() == 10
    }

    @Test
    void "test search people"(){
        RaResponse swapiPeople = Swapi.searchByName("Skywalker")
        Map<String,Object> body = swapiPeople.bodyAsMap

        assert swapiPeople.status ==200
        assert body.count == 3
        assert body.results[0].name == "Luke Skywalker"
        assert body.results[1].name == "Anakin Skywalker"
        assert body.results[2].name == "Shmi Skywalker"
        assert body.results.size() == 3
    }

    @Test
    void "test get person by its ID"(){
        RaResponse swapiPeople = Swapi.getPeopleById("1")
        Map<String,Object> body = swapiPeople.bodyAsMap

        assert swapiPeople.status ==200
        assert body.name == "Luke Skywalker"
        assert body.films.size() == 4
    }


}
