package steps;

import com.google.gson.Gson;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import pojos.Bookingdates;
import pojos.RequestBooking;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BookingAPISteps {

    Response response;
    String bookingId;
    RequestBooking requestBooking;
    Map<String,Object> updatedData;
    Bookingdates bookingdates;

    @Given("user create booking with post api call with data")
    public void user_create_booking_with_post_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        /*
        POST Booking
         */

        Map<String,Object> requestMap=dataTable.asMap(String.class, Object.class);

        requestBooking=new RequestBooking();
        requestBooking.setFirstname(requestMap.get("firstname").toString());
        requestBooking.setLastname(requestMap.get("lastname").toString());
        requestBooking.setTotalprice(Integer.valueOf(requestMap.get("totalprice").toString()));
        requestBooking.setDepositpaid(Boolean.valueOf(requestMap.get("depositpaid").toString()));
        requestBooking.setAdditionalneeds(requestMap.get("additionalneeds").toString());

        bookingdates=new Bookingdates();
        bookingdates.setCheckin(requestMap.get("checkin").toString());
        bookingdates.setCheckout(requestMap.get("checkout").toString());

        requestBooking.setBookingdates(bookingdates);

        // Serialization
        Gson gson=new Gson();
        String jsonStr=gson.toJson(requestBooking);

        response=given().baseUri("https://restful-booker.herokuapp.com")
                .and().header("Accept","application/json")
                .and().header("Content-Type","application/json")
                .and().log().all()
                .and().body(requestBooking) // Java Object -> JSON  === Serialization
                .when().post("/booking");
        response.then().log().all();
        response.then().statusCode(200);
        bookingId=response.body().jsonPath().getString("bookingid");
    }

    @When("user gets created booking with get api call")
    public void user_gets_created_booking_with_get_api_call() {
        /*
        GET Call
         */
        response=given().baseUri("https://restful-booker.herokuapp.com")
                .and().header("Accept","application/json")
                .and().log().all()
                .when().get("/booking/"+bookingId);
        response.then().log().all();
    }

    @Then("user validates created data matches with get response")
    public void user_validates_created_data_matches_with_get_response() {
        // response -> GET Response body -> Actual
        // requestBooking -> POST Request body -> Expected
        Assert.assertEquals(requestBooking.getFirstname(), response.body().jsonPath().getString("firstname"));

        response.then().body("firstname", Matchers.equalTo(requestBooking.getFirstname()))
                .body("lastname", Matchers.equalTo(requestBooking.getLastname()))
                .body("depositpaid",Matchers.equalTo(requestBooking.getDepositpaid()))
                .body("bookingdates.checkin",Matchers.equalTo(requestBooking.getBookingdates().getCheckin()));

        // JSON -> Java Object === Deserialization
        RequestBooking booking=response.body().as(RequestBooking.class);

        // GSON Library
        // Json -> Java Object
        Gson gson=new Gson();
        RequestBooking booking1=gson.fromJson(response.body().toString(),RequestBooking.class);

        Assert.assertEquals(requestBooking.getFirstname(), booking.getFirstname());
        //...

    }

    @When("user updates created booking with put api call with data")
    public void user_updates_created_booking_with_put_api_call_with_data(io.cucumber.datatable.DataTable dataTable) {
        updatedData=dataTable.asMap(String.class,Object.class);

        requestBooking.setFirstname(updatedData.get("firstname").toString());
        bookingdates.setCheckin(updatedData.get("checkin").toString());
        requestBooking.setBookingdates(bookingdates);

        /*
        PUT Call
         */
        response=given().baseUri("https://restful-booker.herokuapp.com")
                .and().accept("application/json")
                .and().contentType("application/json")
                .and().header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .and().body(requestBooking)
                .and().log().all()
                .when().put("/booking/"+bookingId);
        response.then().log().all();
        response.then().statusCode(200);
    }

    @Then("user validates updated data matches with get response")
    public void user_validates_updated_data_matches_with_get_response() {
        // updatedData -> expected data
        // response from get call -> actual data
        RequestBooking responseData=response.body().as(RequestBooking.class); // JSON -> RequestBooking === Deserialization
        Assert.assertEquals(updatedData.get("firstname").toString(), responseData.getFirstname());
        Assert.assertEquals(updatedData.get("checkin").toString(), responseData.getBookingdates().getCheckin());
    }

    @When("user deletes booking with delete api call")
    public void user_deletes_booking_with_delete_api_call() {

        response=given().baseUri("https://restful-booker.herokuapp.com")
                .and().contentType("application/json")
                .and().body("{\n" +
                        "    \"username\" : \"admin\",\n" +
                        "    \"password\" : \"password123\"\n" +
                        "}")
                .and().log().all()
                .when().post("/auth");
        response.then().log().all();
        String token=response.body().jsonPath().getString("token");

        /*
        DELETE Call
         */
        response=given().baseUri("https://restful-booker.herokuapp.com")
                .and().header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
                .and().header("Cookie","token=<"+token+">")
                .and().log().all()
                .when().delete("/booking/"+bookingId);
        response.then().log().all();
        response.then().statusCode(201);
    }

    @Then("user validates {int} status code")
    public void user_validates_status_code(Integer statusCode) {
        response.then().statusCode(statusCode);
    }

}
