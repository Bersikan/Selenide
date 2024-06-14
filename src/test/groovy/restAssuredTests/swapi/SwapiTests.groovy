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

        assert swapiPeople.status ==200
        assert swapiPeople.bodyAsMap.count == 82
        assert swapiPeople.bodyAsMap.results[0].name == "Luke Skywalker"
        assert swapiPeople.bodyAsMap.results.size() == 10
    }

    @Test
    void "test search people"(){
        RaResponse swapiPeople = Swapi.searchByName("Skywalker")

        assert swapiPeople.status ==200
        assert swapiPeople.bodyAsMap.count == 3
        assert swapiPeople.bodyAsMap.results[0].name == "Luke Skywalker"
        assert swapiPeople.bodyAsMap.results[1].name == "Anakin Skywalker"
        assert swapiPeople.bodyAsMap.results[2].name == "Shmi Skywalker"
        assert swapiPeople.bodyAsMap.results.size() == 3
    }

    @Test
    void "test get person by its ID"(){
        RaResponse swapiPeople = Swapi.getPeopleById("1")

        assert swapiPeople.status ==200
        assert swapiPeople.bodyAsMap.name == "Luke Skywalker"
        assert swapiPeople.bodyAsMap.films.size() == 4
    }


}
