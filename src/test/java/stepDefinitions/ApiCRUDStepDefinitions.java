package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;

public class ApiCRUDStepDefinitions {

    private Response response;
    private String baseUri = "https://gorest.co.in";
    private static int userId = 6940092;
    private static final String AUTH_TOKEN = "11b4179e037d7a917e7adc9c04e0ce6dfa04260d0e47b9d5adda7d4d7591076e";

    @Given("I set POST user service api endpoint")
    public void setPostUserServiceApiEndpoint() {
        RestAssured.baseURI = baseUri;
    }

    @When("I send POST HTTP request to create a user with {string}, {string}, {string}, {string}, {string}")
    public void sendPostHttpRequestToCreateUser(String id, String name, String email, String gender, String status) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", id);
        requestParams.put("name", name);
        requestParams.put("email", email);
        requestParams.put("gender", gender);
        requestParams.put("status", status);

        response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", AUTH_TOKEN)
                .body(requestParams.toString())
                .when()
                .post("/public/v2/users");

    }

    @When("I send POST HTTP request without authorization to create a user with {string}, {string}, {string}, {string}, {string}")
    public void sendPostHttpRequestWithoutAuthorizationToCreateUser(String id, String name, String email, String gender, String status) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", id);
        requestParams.put("name", name);
        requestParams.put("email", email);
        requestParams.put("gender", gender);
        requestParams.put("status", status);

        response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toString())
                .when()
                .post("/public/v2/users");
    }

    @Then("the response should contain the created user details")
    public void responseShouldContainCreatedUserDetails() {
        response.then().body("[0].name", equalTo("Deveshwar Abbott"));
        response.then().body("[0].email", equalTo("deveshwar_abbott@johnston.test"));
        response.then().body("[0].gender", equalTo("female"));
        response.then().body("[0].status", equalTo("active"));
    }

    @Given("I set GET user service api endpoint")
    public void setGetUserServiceApiEndpoint() {
        RestAssured.baseURI = baseUri;
    }

    @When("I send GET HTTP request to get the created user with {int}")
    public void sendGetHttpRequestToGetCreatedUser(int userId) {
        response = given()
                .when()
                .get("/public/v2/users/" + userId);
    }

    @Then("the response should contain the user details")
    public void responseShouldContainUserDetails() {
        response.then().body("id", equalTo(userId));
        response.then().body("name", equalTo("Somnath Kaniyar"));
    }

    @Given("I set PUT user service api endpoint")
    public void setPutUserServiceApiEndpoint() {
        RestAssured.baseURI = baseUri;
    }

    @When("I send PUT HTTP request to update the user with {int}")
    public void sendPutHttpRequestToUpdateUser(int userId) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("id", "6940092");
        requestParams.put("name", "Deveshwar Abbottss");
        requestParams.put("email", "deveshwar_abbott123@johnston.test");
        requestParams.put("gender", "female");
        requestParams.put("status", "active");

        response = given()
                .header("Content-Type", "application/json")
                .body(requestParams.toString())
                .when()
                .put("/public/v2/users/" + userId);
    }

    @Then("the response should contain the updated user details")
    public void responseShouldContainUpdatedUserDetails() {
        response.then().body("name", equalTo("Deveshwar Abbottss"));
        response.then().body("email", equalTo("deveshwar_abbott123@johnston.test"));
    }

    @Given("I set DELETE user service api endpoint")
    public void setDeleteUserServiceApiEndpoint() {
        RestAssured.baseURI = baseUri;
    }

    @When("I send DELETE HTTP request to delete the user")
    public void sendDeleteHttpRequestToDeleteUser() {
        response = given()
                .when()
                .delete("/public/v2/users/6940093");
    }

    @When("I send DELETE HTTP request to delete the user with {int}")
    public void sendDeleteHttpRequestToDeleteUser(int userId) {
        response = given()
                .when()
                .delete("/public/v2/users/"+ userId);
    }

    @Then("I receive valid HTTP response code {int}")
    public void receiveValidHttpResponseCode200(int code) {
        response.then().statusCode(code);
    }
}
