package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class YardsAPISteps {

    String requestBodyWithNoName="{\n" +
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
            "}";
    String requestBodyWithNameSpecialChars="{\n" +
            "  \"location\": \"Mindktek 9#$%\",\n" +
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
            "}";
    String requestBodyWithInvalidStatus="{\n" +
            "  \"location\": \"Mindktek 9\",\n" +
            "  \"name\": \"\",\n" +
            "  \"status\": \"closed\",\n" +
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
            "}";
    String id;
    Map<String, Object> requestData;
    Response response;

    @Given("user create yard with post api call")
    public void user_create_yard_with_post_api_call(io.cucumber.datatable.DataTable dataTable) {
        requestData=dataTable.asMap(String.class, Object.class);
        String yardName=requestData.get("yardName").toString();
        String city=requestData.get("city").toString();
        /*
        POST Yard
         */
        response=given().baseUri("http://3.17.122.25/en-us/api/v2")
                .and().header("Accept","application/json")
                .and().header("Authorization","Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().header("Content-Type","application/json")
                .and().log().all()
                .and().body("{\n" +
                        "  \"location\": \""+yardName+"\",\n" +
                        "  \"name\": \"\",\n" +
                        "  \"status\": \"active\",\n" +
                        "  \"address\": \"123 Random St\",\n" +
                        "  \"apt_suite_company_co\": \"\",\n" +
                        "  \"city\": \""+city+"\",\n" +
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
        id=response.body().jsonPath().getString("id");
    }

    @When("user gets created yard with get api call")
    public void user_gets_created_yard_with_get_api_call() {
        response=given().baseUri("http://3.17.122.25/en-us/api/v2")
                .and().header("Accept","application/json")
                .and().header("Authorization","Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().log().all()
                .when().get("/yards/"+id+"/");
        response.then().statusCode(200);
        response.then().log().all();
    }

    @Then("user validates yard details matches with created yard")
    public void user_validates_yard_details_matches_with_created_yard() {
        // requestData -> expected data
        // response -> actual data
        Assert.assertEquals(requestData.get("yardName").toString(), response.body().jsonPath().getString("location"));
        Assert.assertEquals(requestData.get("city").toString(), response.body().jsonPath().getString("city"));
    }

    @Given("user create yard with post api call {string}")
    public void user_create_yard_with_post_api_call_without_yard_name(String scenario) {
        String requestBody="";
        if(scenario.equals("without yard name")){
            requestBody=requestBodyWithNoName;
        }else if(scenario.equals("name with special characters")){
            requestBody=requestBodyWithNameSpecialChars;
        }else if(scenario.equals("with closed status")){
            requestBody=requestBodyWithInvalidStatus;
        }

        response=given().baseUri("http://3.17.122.25/en-us/api/v2")
                .and().header("Accept","application/json")
                .and().header("Authorization","Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().header("Content-Type","application/json")
                .and().log().all()
                .and().body(requestBody)
                .when().post("/yards/");
        response.then().log().all();
    }

    @Then("user validates status code {int}")
    public void user_validates_status_code(Integer statusCode) {
        response.then().statusCode(statusCode);
    }
    @Then("user validates {string} error message")
    public void user_validates_error_message(String errorMessage) {
        String actualErrorMessage=response.body().jsonPath().getString("location[0]");
        System.out.println("Error message is: "+actualErrorMessage);
        Assert.assertEquals(errorMessage,actualErrorMessage);
    }

    @Given("user gets yards with get yard api call with limit {int}")
    public void user_gets_yards_with_get_yard_api_call_with_limit(Integer limit) {
        response=given().baseUri("http://3.17.122.25/en-us/api/v2")
                .and().header("Accept","application/json")
                .and().header("Authorization","Token 9d3994dd2afd7d1d8ae9ecf4d77e45932bb210d6")
                .and().queryParam("limit",limit)
                .and().log().all()
                .when().get("/yards");
        response.then().log().all();
        response.then().statusCode(200);
    }

    @Then("user validates response includes {int} yards")
    public void user_validates_response_includes_yards(int expectedNumberOfYards) {
        List<Object> results=response.body().jsonPath().getList("results");
        Assert.assertEquals(expectedNumberOfYards, results.size());
    }

}
