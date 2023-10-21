package utitlies;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APITest {

    public static void main(String[] args) {

        /*
        GET Yard API Call
        Request:
            URL -> BaseURL + Endpoint
            Headers -> Accept, Authorization
        Response:
            Status Code
            Data(Json)
         */

        // Request
        given().baseUri("http://3.17.122.25/en-us/api/v2")
                .and().header("Accept","application/json")
                .and().header("Authorization","Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().log().all() // log/print all details of request
                .when().get("/yards/100/")
                // Response
                .then().statusCode(200)
                .and().log().all(); // log/print all details of response

        /*
        POST Yard API Call
        Request
            URL -> Base URL + Endpoint
            Headers -> Accept, Authorization, Content-Type
            Data
        Response
            Status Code
            Body
         */

        Response response=given().baseUri("http://3.17.122.25/en-us/api/v2")
                .and().header("Accept","application/json")
                .and().header("Authorization","Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().header("Content-Type","application/json")
                .and().log().all()
                .and().body("{\n" +
                        "  \"location\": \"Mindktek 9 - Club\",\n" +
                        "  \"name\": \"\",\n" +
                        "  \"status\": \"active\",\n" +
                        "  \"address\": \"123 Random St\",\n" +
                        "  \"apt_suite_company_co\": \"\",\n" +
                        "  \"city\": \"Schaumburg\",\n" +
                        "  \"state\": \"IL\",\n" +
                        "  \"zip_code\": \"60173\",\n" +
                        "  \"spots\": \"123\",\n" +
                        "  \"warning\": \"\",\n" +
                        "  \"notes\": \"\",\n" +
                        "  \"notes_popup\": \"\",\n" +
                        "  \"yard_pictures\": [],\n" +
                        "  \"contacts\": [],\n" +
                        "  \"service_phone_number\": [\n" +
                        "    {\n" +
                        "      \"service_phone\": \"\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"owner_phone_number\": [\n" +
                        "    {\n" +
                        "      \"owner_phone\": \"\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"trailer_location\": \"\",\n" +
                        "  \"truck_location\": \"\",\n" +
                        "  \"count_phone\": 0\n" +
                        "}")
                .when().post("/yards/");

        response.then().log().all();
        response.then().statusCode(201);

        String yardId=response.body().jsonPath().getString("id");
        System.out.println("Yard id: "+yardId);

        String city=response.body().jsonPath().getString("city");
        System.out.println("City: "+city);



    }

}
