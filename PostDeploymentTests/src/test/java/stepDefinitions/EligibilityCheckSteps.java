package stepDefinitions;

import java.util.List;
import java.util.Arrays;
import org.junit.Assert;
import com.google.gson.JsonObject;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import actions.ReusableActions;
import io.restassured.response.Response;

public class EligibilityCheckSteps {

  private static final String BASE_URL = "http://localhost:8080";
	
	private static JsonObject applicantInputJsonObj;
	private static Response response;
	private static String jsonString;
	private static List<String> actualCardsList;
	private static ReusableActions actions = new ReusableActions();

	@Before
	public void health_check() {
		// Currently assuming swagger to be health check URL, 
		// but ideally it should be replaced with actual service health check endpoint
		response = actions.get_request(BASE_URL, "/swagger-ui.html");
		Assert.assertEquals("HealthCheck Failed", 200, response.getStatusCode());
  }

  @Given("^I am an user with name \"([^\"]*)\"$")
  public void i_am_an_user_with_name(String username) {
		applicantInputJsonObj = actions.create_applicant_input(username);
  }
	
	@Given("^I am a random user$")
	public void i_am_a_random_user() {
		applicantInputJsonObj = actions.create_applicant_input("random");
	}

  @When("^I perform an eligibility check for a credit card$")
  public void i_perform_an_eligibility_check_for_a_credit_card() {
		response = actions.post_request(BASE_URL, "/eligibility/check", applicantInputJsonObj);
  }

	@When("^I perform an eligibility check for a credit card without one \"([^\"]*)\"$")
	public void i_perform_an_eligibility_check_without_a_mandatory_field(String mandatoryFieldName) {
		JsonObject applicantInputWithOneMissingField = actions.remove_one_mandatory_field(applicantInputJsonObj, mandatoryFieldName);
		int keySize = actions.get_keys_size_from_json_obj(applicantInputWithOneMissingField);
		Assert.assertEquals(2, keySize);
		response = actions.post_request(BASE_URL, "/eligibility/check", applicantInputWithOneMissingField);
	}

	@Then("^I should get a status code indicating \"([^\"]*)\"$") 
	public void i_should_get_a_status_code_indicating_success(String expectedStatus) {
		if (expectedStatus.contains("failure")) {
			Assert.assertEquals(400, response.getStatusCode());
		} else {
			Assert.assertEquals(200, response.getStatusCode());
		}
		
  }

  @Then("^I should get \"([^\"]*)\" as my available list of credit cards$")
  public void i_should_get_cardsList_as_my_available_list_of_credit_cards(String expectedCards) {
		actualCardsList = actions.get_cards_list_from_response(response);
		List<String> expectedCardsList = Arrays.asList(expectedCards.split(","));
		Assert.assertTrue(actualCardsList.size() > 0);
		Assert.assertTrue(actualCardsList.equals(expectedCardsList));
  }

	@Then("^I should not be eligible for any credit cards$")
	public void i_should_not_be_eligible_for_any_credit_cards() {
		actualCardsList = actions.get_cards_list_from_response(response);
		Assert.assertTrue(actualCardsList.isEmpty());
	}

}