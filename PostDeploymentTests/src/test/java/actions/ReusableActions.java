package actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Map.Entry;

import com.github.javafaker.Faker;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class ReusableActions {
  private static Faker faker = new Faker();

  public JsonObject create_applicant_input(String username) {
    JsonObject json = new JsonObject();
    json.addProperty("address", faker.address().streetAddress());
		json.addProperty("email", "test+" + System.nanoTime() + "@gmail.com");
    if (username == "random") {
      json.addProperty("name", faker.name().fullName());
    } else {
      json.addProperty("name", username);
    }
    return json;
  }

  public Response post_request(String baseurl,String endpoint,JsonObject input_body) {
    RestAssured.baseURI = baseurl;
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "application/json");
		Response response = request.body(input_body.toString()).post(endpoint);
    return response;
  }

  public Response get_request(String baseurl,String endpoint) {
    RestAssured.baseURI = baseurl;
		RequestSpecification request = RestAssured.given();
		Response response = request.get(endpoint);
    return response;
  }

  public JsonObject remove_one_mandatory_field(JsonObject inputJson, String mandatoryFieldName) {
    inputJson.remove(mandatoryFieldName);
    return inputJson;
  }

  public List<String> get_cards_list_from_response(Response response) {
    String jsonString = response.asString();
    List<String> cardsList = new ArrayList<String>();
    cardsList = JsonPath.from(jsonString).get("eligibleCards");
    return cardsList;
  }

  public String get_default_error_message(Response response) {
    String jsonString = response.asString();
    String message = JsonPath.from(jsonString).get("errors[0].defaultMessage");
    return message;
  }

  public int get_keys_size_from_json_obj(JsonObject jsonObj) {
    List<String> keys = new ArrayList<String>();
    for (Entry<String, JsonElement> e : jsonObj.entrySet()) {
        keys.add(e.getKey());
    }
    return keys.size();
  }


}